import {BrowserRouter, Routes, Route} from 'react-router-dom';

import Register from "./Register.js";
import Home from "./Home.js";
import Login from "./Login.js";
import Header from "./Header.js";
import Configure from './Configure.js';
import UsersList from './UsersList.js';
import UserPage from './UserPage.js';
import Reports from './Reports.js';
import BlackList from './BlackList.js';

export default function App() {
  return (
    <>
      <BrowserRouter>
        <Header/>
        <Routes>
          <Route path='' element={<Home/>}/>;
          <Route path='login' element={<Login/>}/>;
          <Route path='register' element={<Register/>}/>;
          <Route path='configure' element={<Configure/>}/>;
          <Route path='users' element={<UsersList/>}/>;
          <Route path='users/:id' element={<UserPage/>}/>;
          <Route path='reports' element={<Reports/>}/>;
          <Route path='blackList' element={<BlackList/>}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}