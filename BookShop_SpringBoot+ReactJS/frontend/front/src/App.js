import React from 'react';
import './App.css';
import {BrowserRouter as Router , Switch, Route } from 'react-router-dom';
import Registration from './components/Registration'
import Home from './components/Home';
import Login from './components/Login';
import UserPage from './components/UserPage';
import AdminPage from './components/AdminPage';
import CreateAuction from './components/CreateAuction';
import AddBook from './components/AddBook';
import Shop from './components/Shop';
import Auction from './components/Auction';
import NavbarNavigation from './components/NavbarNavigation';


function App() {
  return (
      <Router>
        <NavbarNavigation />
        <Switch>
          <Route exact path="/Auction" component={Auction} />
          <Route exact path="/Login" component={Login} />
          <Route exact path="/UserPage" component={UserPage} />
          <Route exact path="/AdminPage" component={AdminPage} />
          <Route exact path="/Registration" component={Registration} />
          <Route exact path="/CreateAuction" component={CreateAuction} />
          <Route exact path="/AddBook" component={AddBook} />
          <Route exact path="/Home" component={Home} />
          <Route exact path="/Shop" component={Shop} />
          <Route exact path="/" component={Home} />
      </Switch>
    </Router>
  );
}

export default App;
