import React, { useState } from 'react';
import Modal from 'react-modal';
import axios from "axios";

const LoginWindow = ({ isOpen, onRequestClose, onLogin }) => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/event/login', formData);

            const data = response.data;
            console.log(data);

            if (data.success) {
                console.log('Login successful');
            } else {
                console.log('Login failed:', data.message);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <Modal
            isOpen={isOpen}
            onRequestClose={onRequestClose}
            contentLabel="Login Modal"
        >
            <div style={{ margin: '10px' }}>
                <form onSubmit={handleLogin}>
                    <div style={{ margin: '10px' }}>
                        <label htmlFor="username">Enter username: </label>
                        <input
                            type="text"
                            id="username"
                            name="username"
                            value={formData.username}
                            onChange={handleInputChange}
                            style={{ marginRight: '10px' }}
                        />

                        <label htmlFor="password">Enter password: </label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            value={formData.password}
                            onChange={handleInputChange}
                            style={{ marginRight: '10px' }}
                        />

                        <button type="submit" style={{ marginRight: '10px' }}>
                            Log In
                        </button>
                    </div>
                </form>
            </div>
        </Modal>
    );
};

export default LoginWindow;
