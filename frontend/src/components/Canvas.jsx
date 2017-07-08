import React from 'react';

export default class Canvas extends React.Component {
  componentDidMount() {
    this.updateCanvas();
  }
  componentDidUpdate() {
    this.updateCanvas();
  }
  updateCanvas() {
    const ctx = this.refs.canvas.getContext('2d');
    const imgSrc = 'https://source.unsplash.com/random';
    var img = new Image();
    img.src = imgSrc;
    img.onload = function() {
      // do the drawing, starting at (0,0).
      ctx.drawImage(this, 0, 0);
    };
  }
  render() {
    return (
      <canvas ref="canvas" width={1000} height={1000}/>
    );
  }
}
