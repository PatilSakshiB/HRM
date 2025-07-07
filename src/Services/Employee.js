import axios from "axios";

const BASE_URL="http://localhost:8080/employees";

export const listEmployees = () => axios.get(`${BASE_URL}/getAll`);

export const createEmployee = (employee) => axios.post(`${BASE_URL}/add`, employee);

export const getEmployeeById = (employeeId) => axios.get(`${BASE_URL}/getById/${employeeId}`);

export const updateEmployee = (employeeId, employee) => axios.put(`${BASE_URL}/updateEmp/${employeeId}`, employee);

export const deleteEmp = (employeeId) => axios.delete(`${BASE_URL}/deleteEmp/${employeeId}`);