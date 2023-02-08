import React, { Component } from 'react';

class Footer extends Component {

    constructor(props) {
        super(props);
        this.state = {

        }
    }

    render() {
        return (
            <div>
                <footer className ="footer">
                    <span className="text-muted">WeCare @2023</span>
                </footer>
            </div>
        )
    }
}

export default Footer;