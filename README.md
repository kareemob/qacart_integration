## Introduction
Hello👋, Welcome to My first integration test framework! :)

**Tools:**
⚡ Selenium – UI automation
⚡ TestNG – Test Framework
⚡ Allure – Rreporting
⚡ WireMock – API mocking for stable and predictable test scenarios
  
## Obstacles

😮‍💨 WireMock Proxying: Needed to configure browser proxying correctly to intercept requests and return mocked responses, ensuring the frontend correctly received the expected API data.

😮‍💨 Handling API State Changes: Some tests required different user subscription states (e.g., free vs. advanced). Used WireMock scenarios to simulate stateful API responses.

😮‍💨 Lack of Reliable Locators: The UI had no proper data attributes for identifying elements, requiring a mix of CSS selectors and XPath to interact with elements effectively.


  
## Run the test

- 💪 Clone this repo.
- 💪 Run ```mvn clean test``` .
- 💪 Run ```allure serve allure-results``` To generete the Report.

## Thank You! 🥳
 I really enjoyed working on this framework! From designing a structured test approach to tackling real-world challenges with Selenium and WireMock, it was an exciting and rewarding experience. If you have any questions or want to discuss anything, feel free to ask! 🚀

