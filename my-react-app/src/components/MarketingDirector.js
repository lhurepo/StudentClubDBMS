
import React, { useState } from 'react';
import DeleteForm from "./DeleteForm";

//MarketingDirector ( execID : int, name : varchar, team : varchar )
const MarketingDirector = () => {
    const [popupVisible, setPopupVisible] = useState(false);
    const [formData, setFormData] = useState({
        execID: '',
        name: '',
        team: '',
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
            execID: '',
            name: '',
            team: ''
        });
    };

    return (
        <div>
            <div><button type="button" className="btn btn-secondary custom-sub-button" onClick={handleButtonClick}>
                Update Marketing Director
            </button>
                {popupVisible && (
                    <div className="popup" >
                        <div className="popup-content">
                            <form className="lg:w-[50%] w-[35%]" onSubmit={handleSubmit} >
                                <label className="form-title"> Insert New Data :</label><br />
                                <label className="labelStyle">
                                    ID:
                                    <input
                                        type="text"
                                        name="execID"
                                        value={formData.execID}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Name:
                                    <input
                                        type="text"
                                        name="name"
                                        value={formData.name}
                                        onChange={handleInputChange}
                                    />
                                </label>
                                <br />

                                <label className="labelStyle">
                                    Team:
                                    <input
                                        type="text"
                                        name="team"
                                        value={formData.team}
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

export default MarketingDirector;