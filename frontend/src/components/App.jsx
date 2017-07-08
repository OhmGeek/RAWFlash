import React from 'react';
import {Router, Route, Link, IndexRoute, hashHistory, browserHistory} from 'react-router';
import {createHashHistory} from 'history'
import Canvas from './Canvas.jsx'
export default class App extends React.Component {
  render() {
    return (
      <Router history={createHashHistory()}>
        <div>
          <Route exact path='/' component={Home}/>
          <Route path='/about' component={HomeTwo}/>
        </div>
      </Router>
    );
  }
}

const Home = () => <Canvas />
const HomeTwo = () => <h1> Hasdfasdf </h1>
