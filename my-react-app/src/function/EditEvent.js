import React, { useState } from 'react';

const EditEvent = ({ dataPopupVisible, setDataPopupVisible, searchID, searchedData, onSave }) => {
    const [editedData, setEditedData] = useState(searchedData);

    const handleInputChange = (propertyName, value) => {
        setEditedData(prevData => ({
            ...prevData,
            [propertyName]: value,
        }));
    };

    const handleSave = () => {
        onSave(editedData);

        setDataPopupVisible(false);
    };

    return (
        dataPopupVisible && (
            <div className="popup">
                <div className="popup-content">
                    <h2>Data for ID: {searchID}</h2>

                    {Object.keys(editedData).map(propertyName => (
                        <div key={propertyName}>
                            <label>{propertyName}:</label>
                            <input
                                type="text"
                                value={editedData[propertyName]}
                                onChange={(e) => handleInputChange(propertyName, e.target.value)}
                            />
                        </div>
                    ))}

                    <button className="btn btn-primary custom-sub-button" onClick={handleSave}>
                        Save
                    </button>
                    <button className="btn btn-secondary custom-sub-button" onClick={() => setDataPopupVisible(false)}>
                        Close
                    </button>
                </div>
            </div>
        )
    );
};

export default EditEvent;
