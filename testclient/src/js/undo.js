// Undo.js - Functionality for Undo.
// Provides an object that manages the undo functionality for the application.

// ---------------- Stack Helpers ----------------

class Stack {
  constructor() {
    this.stackList = [];
  }

  push(data) {
    this.stackList.push(data);
  }

  pop() {
    if(this.stackList.length > 0) {
      return this.stackList.pop();
    } else {
      return null;
    }
  }

  empty() {
    this.stackList = [];
  }

  isEmpty() {
    return this.stackList.length == 0;
  }
}



// ------------ UNDO Manager -----------------

class UndoManager {

  constructor() {
    this.undoStack = new Stack();
    this.redoStack = new Stack();
  }

  addEdit(fieldChanged, oldValue, newValue) {
    if(!this.redoStack.isEmpty()) {
      this.redoStack.empty();
    }

    this.undoStack.push({
      "fieldModified": fieldChanged,
      "oldValue": oldValue,
      "newValue": newValue
    });
  }

  canUndo() {
    return !this.undoStack.isEmpty(); // can undo if at least one undoable modification.
  }

  canRedo() {
    return this.redoStack.length > 0; // can redo if at least one redoable modification.
  }

  undo(data) {
    let modification = this.undoStack.pop();
    if(modification != null) {
      //indicates we can undo.
      let field = modification.fieldModified;
      let val = modification.oldValue;

     // now update the imageSettings
     data[field] = val;

     // Deal with the redo functionality.
     this.redoStack.push(modification);
    }
  }

  redo(data) {
    let modification = this.redoStack.pop();
    if(modification != null) {
      let field = modification.fieldModified;
      let val = modification.newValue;

      data[field] = val;
    }
  }
}
