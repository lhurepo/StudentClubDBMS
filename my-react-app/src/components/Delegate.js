//Delegate( employeeID : int, company : varchar, position : varchar, name : varchar, email : varchar, dietaryRestrictions : varchar, LinkedIn : varchar, eventID : int, execID : int)

import React, { useState } from "react";
import DeleteForm from "./DeleteForm";

const Delegate = () => {
    const [popupVisible, setPopupVisible] = useState(false);
    const [formData, setFormData] = useState({
        employeeID: '',
        company: '',
        position: '',
        name: '',
        email: '',
        dietaryRestrictions: '',
        LinkedIn: '',
        eventID: '',
        execID: '',
    })
    const handleButtonClick = () => {
        setPopupVisible(!popupVisible);
    };
    const closePopup = () => {
        setPopupVisible(false);
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Form submitted:', formData);
        closePopup();
    };

    const handleReset = () => {
        setFormData({
            employeeID: '',
            company: '',
            position: '',
            name: '',
            email: '',
            dietaryRestrictions: '',
            LinkedIn: '',
            eventID: '',
            execID: '',
        });
    };

    return (
        <div>
            <div><button type="button" className="btn btn-secondary custom-sub-button" onClick={handleButtonClick}>
                Update Delegate
            </button>
                {popupVisible && (
                    <div className="popup" >
                        <div className="popup-content">
                            <form className="lg:w-[50%] w-[35%]" onSubmit={handleSubmit} >
                                <label className="form-title"> Insert New Data :</label><br />
                                <label className="labelStyle">
                                    EmployeeID :
                                    <input
                                        type="number"
                                        name="employeeID"
                                        value={formData.employeeID}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Company :
                                    <input
                                        type="text"
                                        name="company"
                                        value={formData.company}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />


                                <label className="labelStyle">
                                    Position :
                                    <input
                                        type="text"
                                        name="position"
                                        value={formData.position}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Name :
                                    <input
                                        type="text"
                                        name="name"
                                        value={formData.name}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Email:
                                    <input
                                        type="text"
                                        name="email"
                                        value={formData.email}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Dietary Restrictions:
                                    <input
                                        type="text"
                                        name="dietaryRestrictions"
                                        value={formData.dietaryRestrictions}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    LinkedIn :
                                    <input
                                        type="text"
                                        name="LinkedIn"
                                        value={formData.LinkedIn}
                                        onChange={handleInputChange}
                                    />
                                </label>


                                <label className="labelStyle">
                                    Exec ID:
                                    <input
                                        type="text"
                                        name="execID"
                                        value={formData.execID}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Event ID:
                                    <input
                                        type="text"
                                        name="eventID"
                                        value={formData.eventID}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />




                                <div  >
                                    <button type="reset" onClick={handleReset} className="btn btn-secondary custom-sub-button">
                                        Clear </button>
                                    <button type="submit" onClick={handleSubmit} className="btn btn-secondary custom-sub-button">
                                        Submit </button>
                                    <button onClick={closePopup} className="btn btn-secondary custom-sub-button">Close</button>
                                </div>
                            </form>
                            <div><DeleteForm /> </div>

                        </div>
                    </div>
                )}
            </div>


        </div>);
};

export default Delegate;