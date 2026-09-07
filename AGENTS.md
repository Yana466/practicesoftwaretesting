# Agent Guidance

## Project overview

This repository contains educational end-to-end UI automation examples from the *Mastering Modern Test Automation With Playwright In Java* course. It is a Maven project targeting Java 17 and uses Playwright for browser automation with JUnit 5 for test execution.

## Repository layout

- `pom.xml` — Maven build configuration, Java 17 compiler settings, Playwright, and JUnit 5 dependencies.
- `src/test/java/com/practicesoftwaretesting/` — Toolshop example tests, page objects, and shared test lifecycle support.
- `src/test/java/config/` — Shared test configuration such as Playwright launch settings.


## Development conventions
- Keep test scenarios in `tests` packages and UI interactions/selectors in `pages` page-object classes. Do not put page-object behavior directly into test classes unless the example specifically demonstrates that approach.
- Reuse the existing `BaseTest` lifecycle support for Playwright setup and cleanup in the corresponding package.
- Prefer stable, user-facing locators and clear assertions. Keep changes focused on the current example/module and follow the existing Java naming and package conventions.


## Common commands
Run tests:
```bash
mvn test
```

Run specific tests (replace the param value with the class name of your test):
```bash
mvn test -Dtest=com.practicesoftwaretesting.tests.PurchaseFlowTest
```


The tests currently launch Chromium in headed mode with a configured slow-motion delay. A local environment therefore needs a graphical session, and Playwright browser/dependency installation may be required before the first run.


## Branch and repository safety
- Do not change course-module structure or unrelated examples as part of a focused task.
- Preserve user changes and inspect the current branch state before making broad edits.

## Validation
After Java or Maven changes, run the narrowest relevant test first, then full `mvn test` when practical. Report any environment-related failures separately from code failures (for example, missing Playwright browsers or a headless-display limitation).

