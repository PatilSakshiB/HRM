import { useState } from 'react'
import ListEmployee from './Components/ListEmployee'
import Header from './Components/Header'
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import AddEmployee from './Components/AddEmployee';

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <BrowserRouter>
      <Header/>
      <Routes>
        {/*  http://localhost:3000 */}
        <Route path='/' element={<ListEmployee/>}></Route>
          {/* http://localhost:3000/employees */}
        <Route path='/employees' element={<ListEmployee />} />
          {/* http://localhost:3000/add-employee */}
        <Route path='/add-employee' element={<AddEmployee/>}></Route>
         {/* http://localhost:3000/edit-employee/1 */}
        <Route path='/edit-employee/:id' element={<AddEmployee/>}></Route>
      </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
