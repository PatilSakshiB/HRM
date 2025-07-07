import React from 'react'
import { useEffect } from 'react';
import { useState } from 'react'
import { deleteEmp, listEmployees } from '../Services/Employee';
import { useNavigate } from 'react-router-dom';

const ListEmployee = () => {

    const [employees, setEmployees] = useState([]);
    const navigator = useNavigate();

    useEffect( () => {
       getAllEmplyee()}, [])

    function getAllEmplyee(){
         listEmployees().then((response) => {
            setEmployees(response.data);
        }).catch(error => {
            console.error(error);
        });
    }

    function addEmployee(){
        navigator('/add-employee')
    }
    
    function updateEmployee(id){
    navigator(`/edit-employee/${id}`)
    }

    function deleteEmployee(id){
        deleteEmp(id).then((response) => {
            getAllEmplyee();
        }).catch((error)=>{
            console.error(error);
        })
    }

  return (
    <>
        <div className='container m-4'>
            <button type="button" className="btn btn-primary" onClick={addEmployee}>Add Employee</button>
            <table className="table table-striped mt-4">
                <thead>
                    <tr>
                    <th scope="col">Employee Id</th>
                    <th scope="col">Full Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Phone No</th>
                    <th scope="col">Department</th>
                    <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        employees.map(employee =>
                            <tr key={employee.id}>
                                <td>{employee.id}</td>
                                <td>{employee.fullName}</td>
                                <td>{employee.email}</td>
                                <td>{employee.phoneNo}</td>
                                <td>{employee.department}</td>
                                <td><button className="btn btn-info mx-2" onClick={() => updateEmployee(employee.id)}>Update</button>
                                    <button className="btn btn-danger" onClick={() => deleteEmployee(employee.id)}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    </>
  )
}

export default ListEmployee