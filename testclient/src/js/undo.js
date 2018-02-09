// Undo.js - Functionality for Undo.
// Provides an object that manages the undo functionality for the application.

function Stack() {
  this.stackList = []
}

Stack.prototype.push = function(data) {
  this.stackList.append(data);
};

Stack.prototype.pop = function() {
  if(this.stackList.length > 0) {
    return this.stackList.pop();
  } else {
    return null;
  }
};
Stack.prototype.empty = function() {
  this.stackList = [];
}

Stack.prototype.isEmpty = function() {
  return this.stackList.length == 0;
}
function UndoManager() {
  this.undoStack = new Stack();
  this.redoStack = new Stack();  
}

UndoManager.prototype.addEdit = (fieldChanged, oldValue, newValue) => {
  // when adding an edit after an undo, we can't redo anything.
  if(!this.redoStack.isEmpty()) {
    this.redoStack.empty();
  }

  this.undoStack.push({
    "fieldModified": fieldChanged,
    "oldValue": oldValue,
    "newValue": newValue
  });
};

UndoManager.prototype.canUndo = () => {
  return !this.undoStack.isEmpty(); // can undo if at least one undoable modification.
};

UndoManager.prototype.canRedo = () => {
  return this.redoStack.length > 0; // can redo if at least one redoable modification.
};

UndoManager.prototype.undo = (imageSettings) => {
  let modification = this.undoStack.pop();
  if(modification != null) {
    //indicates we can undo.
    let field = modification.fieldModified;
    let val = modification.oldValue;

   // now update the imageSettings
   imageSettings[field] = [val];

   // Deal with the redo functionality.
   this.redoStack.push(modification); 
  }
}
