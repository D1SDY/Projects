import React, { Component } from 'react';
import {Nav , Navbar } from "react-bootstrap";


/**
 * Navbar will apear in every page in our aplication . 
 * You can go to book shop or to your accont if you logged in (than you will see your username and balance)
 * You can log out log in
 * It looking what role have user and will show Admin page or User page due to role **/
class NavbarNavigation extends Component {

  constructor(props) {
    super(props);
    this.state = {
      isTokenPresent: false,
      userRole:'' ,
      userBalance: '' , 
      userLogin: ''
    }

    this.triggerLogoutClick = this.triggerLogoutClick.bind(this);
  }

  componentDidMount() {
    localStorage.getItem('token') ? this.setState({isTokenPresent: true}) : this.setState({isTokenPresent: false});
    let role = JSON.parse(localStorage.getItem('role'));
    console.log('role', role);
    const token = JSON.parse(localStorage.getItem('token'));
    this.setState({
      userRole: role
    })
    
  if(localStorage.getItem('token')){
    fetch('/users/current', {
      method: 'GET',
      headers: {
          'Authorization': `Bearer ${token}`
      }
    })
    .then((response) => response.json())
    .then((data) => {
        console.log('data', data);

      this.setState({
          userBalance: data.balance,
          userLogin: data.login
      });
      
  });
  }
      
    
      

    
  }
  

  triggerLogoutClick = (event) => {
      localStorage.clear();
    // let token = JSON.parse(localStorage.getItem('token'));

    // token = null;

    // localStorage.setItem('token',JSON.stringify(token));
    // this.setState({
    //   isTokenPresent: false
    // });
  }

  render() {
    return(
      <Navbar bg="dark" variant="dark"  sticky="top">
      <Navbar.Brand href="/Home">XentaiLib</Navbar.Brand>
        <Nav className="mr-auto">
    <Nav.Link href = {this.state.userRole === 'admin' ? '/AdminPage' : '/UserPage'}>{this.state.userLogin} {this.state.userBalance}</Nav.Link>
          <Nav.Link href="/Shop">Shop</Nav.Link>
        </Nav>
        <Nav>
          <Nav.Link href="/Registration" style = {{display: this.state.isTokenPresent ? 'none' : 'block'}}>Registration</Nav.Link>
          <Nav.Link href="/Login" style = {{display: this.state.isTokenPresent ? 'none' : 'block'}}>Login</Nav.Link>
        
          <Nav.Link href="/Login" style={{display: this.state.isTokenPresent ? 'block' : 'none'}} onClick={this.triggerLogoutClick}>Logout</Nav.Link>
          
          
        </Nav>
      </Navbar>
    )
  }

}

export default NavbarNavigation;
