import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { createEmployee, getEmployeeById, updateEmployee } from '../Services/Employee';

const AddEmployee = () => {

  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [phoneNo, setPhoneNo] = useState('');
  const [department, setDepartment] = useState('');
  const [errors, setErrors] = useState({
          fullName:'',
          email:'',
          phoneNo:'',
          department:''
      })
  
  const {id} = useParams();
  const navigator = useNavigate();

  useEffect(() => {
  if (id) {
    getEmployeeById(id).then((response) => {
      setFullName(response.data.fullName);
      setEmail(response.data.email);
      setPhoneNo(response.data.phoneNo);
      setDepartment(response.data.department);
    }).catch(err => {
      console.error(err);
    });
  }
}, [id]);


function saveOrUpdateEmployee(e){
  e.preventDefault();
  if(validateForm()){
    const emp={fullName,phoneNo,email,department};
    if(id){
      updateEmployee(id,emp).then((response) =>{
        console.log(response.data);
         navigator('/employees');
      }).catch((error) => {
      console.error(error);
    })
    }else{
       createEmployee(emp).then((response)=>{
        console.log(response.data);
        navigator('/employees');
      }).catch((error) => {
         console.error(error);
      })
    }
 
 
  }
}

function validateForm(){
  let valid=true;
  const errorCopy={...errors};
  if(fullName.trim()){
    errorCopy.fullName = '';
  }else{
    errorCopy.fullName = 'Full Name is required';
    valid=false;
  }
   if(email.trim()){
    errorCopy.email = '';
  }else{
    errorCopy.email = 'Email is required';
    valid=false;
  }
   if(phoneNo.trim()){
    errorCopy.phoneNo = '';
  }else{
    errorCopy.phoneNo = 'Phone no. is required';
    valid=false;
  }
   if(department.trim()){
    errorCopy.department = '';
  }else{
    errorCopy.department = 'Department is required';
    valid=false;
  }
  setErrors(errorCopy); 
  return valid;
}

function pageTitle(){
  if(id){
    return  <h2 className='text-center m-2'>Update Employee</h2>;
  }else{
    return  <h2 className='text-center m-2'>Add Employee</h2>;
  }
}

  return (
    <>
      <div className="container">
        <br />
        <div className="row">
          <div className="card col-md-6 offset-md-3 offset-md-3">
        {pageTitle()}
        <div className="card-body">
          <form action="">
            <div className="form-group mb-2">
              <label htmlFor="fullName" className="form-label">Full Name: </label>
              <input type="text" placeholder='Enter Employee Name' name='fullName' value={fullName} 
                className={`form-control ${errors.fullName ? 'is-invalid':''}`} onChange={ (e) => setFullName(e.target.value)} />
                {errors.fullName && <div className='invalid-feedback'> {errors.fullName}</div>}
            </div>
            <div className="form-group mb-2">
              <label htmlFor="email" className="form-label">Email: </label>
              <input type="text" placeholder='Enter Employee Email' name='email' value={email} 
                className={`form-control ${errors.email ? 'is-invalid':''}`} onChange={ (e) => setEmail(e.target.value)} />
                {errors.email && <div className='invalid-feedback'> {errors.email}</div>}
            </div>
             <div className="form-group mb-2">
              <label htmlFor="phoneNo" className="form-label">Phone No.: </label>
              <input type="text" placeholder='Enter Employee Phone No.' name='phoneNo' value={phoneNo} 
                className={`form-control ${errors.phoneNo ? 'is-invalid':''}`} onChange={ (e) => setPhoneNo(e.target.value)} />
                {errors.phoneNo && <div className='invalid-feedback'> {errors.phoneNo}</div>}
            </div>
             <div className="form-group mb-2">
              <label htmlFor="department" className="form-label">Department: </label>
              <input type="text" placeholder='Enter Employee Department' name='department' value={department} 
                className={`form-control ${errors.department ? 'is-invalid':''}`} onChange={ (e) => setDepartment(e.target.value)} />
                {errors.department && <div className='invalid-feedback'> {errors.department}</div>}
            </div>
            <button className="btn btn-success" onClick={saveOrUpdateEmployee}>Submit</button>
          </form>
        </div>
      </div>
        </div>
      </div>
    </>

  )
}

export default AddEmployee