import React, { useState, useEffect } from 'react';

const DeleteForm = () => {
    const [inputValue, setInputValue] = useState('');
    const [data, setData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch('https://mocki.io/v1/8de749a8-aaff-4226-ad8c-7d9bafa586a1');
                const jsonData = await response.json();
                setData(jsonData);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []);

    const handleInputChange = (e) => {
        setInputValue(e.target.value);
    };

    const handleDelete = (e) => {
        e.preventDefault();

        if (!inputValue.trim()) {
            alert('Please enter a valid ID to delete.');
            return;
        }

        const itemToDelete = data.find(item => item.id === inputValue);

        if (!itemToDelete) {
            alert('Item not found with the specified ID.');
            return;
        }

        console.log('Deleting item:', itemToDelete);

        setInputValue('');
    };

    return (
        <div>
            <form onSubmit={handleDelete}>
                <label className="form-title"> Delete Data : </label>
                <label>
                    <input
                        type="text"
                        value={inputValue}
                        onChange={handleInputChange}
                        placeholder="Enter ID to Delete"
                    />
                </label>
                <br />
                <button type="submit" className="btn btn-secondary custom-sub-button">
                    Delete
                </button>
            </form>
        </div>
    );
};

export default DeleteForm;
