import shutil
import subprocess
import time
from datetime import datetime
from pathlib import Path

from utils.driver_factory import get_driver

ALLURE_RESULTS_DIR = Path(__file__).resolve().parents[1] / "reports" / "allure-results"
ALLURE_REPORTS_BASE = Path(__file__).resolve().parents[1] / "reports" / "allure-reports"


def before_all(context):
    print("Setting up the test environment...")


def after_all(context):
    print("Tearing down the test environment...")
    try:
        generate_allure_report()
    except Exception as exc:
        print(f"Could not generate Allure report: {exc}")
        print("Skipping Allure report generation.")


def before_scenario(context, scenario):
    print(f"Setting up for scenario: {scenario.name}")
    if "ui" in scenario.tags:
        context.driver = get_driver()


def after_scenario(context, scenario):
    print(f"Tearing down after scenario: {scenario.name}")
    if hasattr(context, "driver"):
        time.sleep(1)
        context.driver.quit()


def generate_allure_report():
    timestamp = datetime.now().strftime("%Y-%m-%d_%H-%M-%S")
    date_folder = datetime.now().strftime("%Y-%m-%d")
    target_dir = ALLURE_REPORTS_BASE / date_folder
    target_dir.mkdir(parents=True, exist_ok=True)

    temp_dir = target_dir / f"temp_allure_report_{timestamp}"
    temp_dir.mkdir(parents=True, exist_ok=True)

    allure_cmd = shutil.which("allure") or shutil.which("allure.cmd") or shutil.which("allure.bat") or shutil.which("allure.exe")
    if allure_cmd is None:
        raise RuntimeError(
            "Allure CLI was not found in PATH. Install Allure CLI to generate HTML reports. "
            "Without it, Behave will still run but the final Allure report cannot be created."
        )

    print(f"Using Allure CLI: {allure_cmd}", flush=True)
    print(f"Generating Allure report from: {ALLURE_RESULTS_DIR}", flush=True)
    print(f"Output temp directory: {temp_dir}", flush=True)

    try:
        subprocess.run(
            [
                allure_cmd,
                "generate",
                str(ALLURE_RESULTS_DIR),
                "-o",
                str(temp_dir),
                "--clean",
                "--single-file",
            ],
            check=True,
            capture_output=True,
            text=True,
        )
    except subprocess.CalledProcessError as exc:
        print("Allure generation failed:", exc.stderr, flush=True)
        raise

    generated_files = list(temp_dir.glob("*.html"))
    if not generated_files:
        raise RuntimeError(
            f"Allure report generation completed, but no HTML file was found in {temp_dir}."
        )

    final_report = target_dir / f"testreport_{timestamp}.html"
    generated_files[0].replace(final_report)

    # Remove the temporary directory and all of its contents.
    shutil.rmtree(temp_dir, ignore_errors=True)

    print(f"Generated Allure report: {final_report}", flush=True)
