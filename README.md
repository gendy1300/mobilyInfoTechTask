# BugIt Android App

BugIt is an Android application developed using Jetpack Compose and MVI (Model-View-Intent) architecture. It serves as a bug tracking system, allowing users to report bugs along with a photo, description, title, reporter name, and date. Bugs are uploaded into a Google Sheets document for easy tracking and management.

## Features

- **Bug Reporting**: Users can report bugs by providing a title, description, and attaching a photo.
- **Google Sheets Integration**: Bug reports are automatically uploaded to a Google Sheets document for centralized tracking.
- **Image Upload**: Photos attached to bug reports are uploaded to a third-party service called ImageBB, and the generated link is used as the photo for the bug report.

## Dependencies

- **Jetpack Compose**: Modern toolkit for building native Android UI.
- **MVI Architecture**: Model-View-Intent architecture for a reactive and unidirectional data flow.
- **Version-catalog**: Version catalog for managing dependencies.

## Installation

1. Clone the repository:
```
git clone https://github.com/yourusername/BugTracker.git
```

2. Open the project in Android Studio.

3. Build and run the app on your device or emulator.

## Usage

1. Launch the BugIt app on your device.

2. Click on the plus icon.

3. Fill in the bug title, description, and attach a photo.

4. Click on the "Create A Bug" button to upload the bug report.

5. Bug reports can be viewed and managed in the Google Sheets document linked to the app or in the app itself.


## License

This project is licensed under the [MIT License](LICENSE).
