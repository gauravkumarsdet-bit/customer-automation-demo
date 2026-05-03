# BDD Automation Summary

## Overview

This folder contains a Python BDD automation framework using `behave`, Selenium, and `allure-behave`.

The framework validates the Employees UI page and generates an Allure HTML report automatically after test execution.

## How to run

From the `bdd-automation` folder in PowerShell:

```powershell
.\venv\Scripts\python.exe -m behave --no-capture
```

This command uses the local virtual environment and shows console output during execution.

## Report generation

After the test run, the framework generates a single HTML report under:

```text
bdd-automation/reports/allure-reports/<YYYY-MM-DD>/testreport_<timestamp>.html
```

For example:

```text
bdd-automation/reports/allure-reports/2026-05-03/testreport_2026-05-03_14-12-32.html
```

## Notes

- The report generation requires the Allure CLI executable to be installed and available on `PATH`.
- If Allure CLI is missing, the tests still run, but the final HTML report will be skipped.
- The `features/environment.py` hook automatically creates the required report directories.

## Key files

- `features/environment.py` - Behave hooks and report generation logic
- `features/employee_ui.feature` - UI validation scenarios
- `features/steps/employee_ui_steps.py` - step definitions for the Employees page
- `pages/employee_page.py` - Selenium page object for the Employees UI
- `reports/allure-results/` - raw Allure results
- `reports/allure-reports/` - generated HTML reports
