import React, { useState } from 'react';

const EditData = () => {
    const [searchId, setSearchId] = useState('');
    const [employeeData, setEmployeeData] = useState(null);

    const handleSearch = async () => {
        try {
            const response = await fetch(`http://dummy.restapiexample.com/api/v1/employee/${searchId}`);
            const data = await response.json();

            if (data.status === 'success') {
                setEmployeeData(data.data);
            } else {
                setEmployeeData(null);
                alert('Employee not found!');
            }
        } catch (error) {
            console.error('Error fetching employee data', error);
        }
    };

    const clearData = () => {
        setSearchId('');
        setEmployeeData(null);
    };

    return (
        <div>
            <label className="form-title">
                Search ID:
                <input
                    type="text"
                    value={searchId}
                    onChange={(e) => setSearchId(e.target.value)}
                />
            </label>
            <button onClick={handleSearch} className="btn btn-secondary custom-sub-button">Search</button>
            <button onClick={clearData} className="btn btn-secondary custom-sub-button">Clear</button>

            {employeeData && (
                <div>
                    <label className="form-title"> Data : </label>
                    <p>ID: {employeeData.id}</p>
                    <p>Name: {employeeData.employee_name}</p>
                    <p>Salary: {employeeData.employee_salary}</p>
                    <p>Age: {employeeData.employee_age}</p>
                </div>
            )}
        </div>
    );
};

export default EditData;
