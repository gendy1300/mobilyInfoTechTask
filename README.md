# BugIt Android App

BugIt is an Android application developed using Jetpack Compose and MVI (Model-View-Intent) architecture. It serves as a bug tracking system, allowing users to report bugs along with a photo, description, title, reporter name, and date. Bugs are uploaded into a Google Sheets document for easy tracking and management.

## Features

- **Bug Reporting**: Users can report bugs by providing a title, description, and attaching a photo.
- **Google Sheets Integration**: Bug reports are automatically uploaded to a Google Sheets document for centralized tracking.
- **Image Upload**: Photos attached to bug reports are uploaded to a third-party service called ImageBB, and the generated link is used as the photo for the bug report.
- **Google Apps Script**: Utilizes a Google Apps Script deployed as a web app to connect to Google Sheets.


## Dependencies

- **Jetpack Compose**: Modern toolkit for building native Android UI.
- **MVI Architecture**: Model-View-Intent architecture for a reactive and unidirectional data flow.
- **Version-catalog**: Version catalog for managing dependencies.

## Google Apps Script Functions

Here's is the complete Google Apps Script functions used to interact with Google Sheets:

```javascript
function doGet(e) {
  var action = e.parameter.action;
  var sheetName = e.parameter.sheetName;
  var ss = SpreadsheetApp.openById("1d4e_o0qAMeUpdjMTIacBEVc7eRJbBc_MTjks2Q3snfU");
 
  
  if(action == "getAllData"){
    return getAllSheetValues(ss)
  }
 else if(action == "addNewSheet"){
    
    var success = createSheetTabInSpreadsheet(ss,sheetName);
    if(success) {
      return ContentService.createTextOutput(JSON.stringify("success"));
    } else {
      return ContentService.createTextOutput(JSON.stringify("failure"));
    }
  }else if(action == "getAllSheetNames"){
    return getAllSheetNames(ss)
  }else if(action == "insertValue"){
    var values = e.parameter.values.split(',');
    var success = insertValuesInLastSheet(ss,values)
     if(success) {
      return ContentService.createTextOutput(JSON.stringify(success));
    } else {
      return ContentService.createTextOutput(JSON.stringify(success));
    }
  }
}

function getAllSheetValues(originalSheet) {
  var sheets = originalSheet.getSheets();
  var allData = [];
  
  sheets.forEach(function(sheet) {
    var dataRange = sheet.getDataRange();
    var values = dataRange.getValues();
    
    // Exclude the first row (header)
    var headers = values.shift();
    
    // Convert each row into an object
    values.forEach(function(row) {
      var rowData = {};
      row.forEach(function(value, index) {
        rowData[headers[index]] = value;
      });
      allData.push(rowData);
    });
  });

  return ContentService
    .createTextOutput(JSON.stringify(allData))
    .setMimeType(ContentService.MimeType.JSON);
}


function createSheetTabInSpreadsheet(originalSheet, sheetName) {
  try {
    // Insert a new sheet
    var newSheet = originalSheet.insertSheet(sheetName);
    
    // Add the first row with specific values
    var firstRowValues = ["title", "description", "photo", "reporterName", "date"];
    var range = newSheet.getRange(1, 1, 1, firstRowValues.length);
    range.setValues([firstRowValues]);
    
    return true; // Sheet tab created successfully with first row
  } catch (error) {
    return false; // Error creating sheet tab
  }
}


function getAllSheetNames(originalSheet) {
  var sheets = originalSheet.getSheets();
  var sheetNames = [];
  
  sheets.forEach(function(sheet) {
    sheetNames.push(sheet.getName());
  });

return ContentService
    .createTextOutput(JSON.stringify(sheetNames))
    .setMimeType(ContentService.MimeType.JSON);
}


function insertValuesInLastSheet(ss, values) {
  var sheets = ss.getSheets();
  var lastSheet = sheets[sheets.length - 1];
  var lastRow = lastSheet.getLastRow() + 1; // Increment to insert a new row
  
  try {
    if (!Array.isArray(values)) {
      throw new Error("Values must be an array.");
    }
    var numColumns = values.length;
    if (numColumns === 0) {
      throw new Error("Values array is empty.");
    }
    var range = lastSheet.getRange(lastRow, 1, 1, numColumns); // Set the range to cover all columns
    range.setValues([values]); // Insert values into the range
    return true; // Values inserted successfully
  } catch (error) {
    console.error("Error inserting values: " + error.message);
    return false; // Error inserting values
  }
}
```

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
