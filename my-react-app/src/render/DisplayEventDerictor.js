import React from "react";

class DisplayEventDirector extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            list: []
        };

        this.callAPI = this.callAPI.bind(this);
        this.callAPI();
    }

    callAPI() {
        fetch("https://mocki.io/v1/8de749a8-aaff-4226-ad8c-7d9bafa586a1")
            .then(response => response.json())
            .then(data => {
                console.log(data);
                this.setState({
                    list: data
                });
            })
            .catch(error => {
                console.error("Error fetching data:", error);
            });
    }

    render() {
        let tb_data;

        if (this.state.list && this.state.list.length > 0) {
            tb_data = this.state.list.map((item) => (
                <tr key={item.id}>
                    <td>{item.execID}</td>
                    <td>{item.name}</td>
                    <td>{item.team}</td>
                    <td>{item.eventID}</td>
                    <td>{item.venueID}</td>
                </tr>
            ));
        } else {
            tb_data = (
                <tr>
                    <td colSpan="5">No data available</td>
                </tr>
            );
        }

        return (
            <div className="container">
                <table className="table table-striped">
                    <thead>
                    <tr>
                        <th>execID</th>
                        <th>name</th>
                        <th>team</th>
                        <th>eventID</th>
                        <th>venueID</th>
                    </tr>
                    </thead>
                    <tbody>{tb_data}</tbody>
                </table>
            </div>
        );
    }
}

export default DisplayEventDirector;
