import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class EventEdit extends Component {

    emptyItem = {
        name: '',
        description: '',
        event_start: '',
        event_finish: '',

    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const event = await (await fetch(`/api/event/${this.props.match.params.id}`)).json();
            this.setState({item: event});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;

        await fetch((item.id) ? '/api/event/'+(item.id) : '/api/event', { //await fetch('/api/event', {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/events');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Event' : 'Add Event'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={item.name || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="description">Description</Label>
                        <Input type="text" name="description" id="description" value={item.description || ''}
                               onChange={this.handleChange} autoComplete="description"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="event_start">Event start</Label>
                        <Input type="event_start" name="event_start" id="event_start" value={item.event_start || ''}
                               onChange={this.handleChange} autoComplete="event_start"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="event_finish">Event finish</Label>
                        <Input type="event_finish" name="event_finish" id="event_finish" value={item.event_finish || ''}
                               onChange={this.handleChange} autoComplete="event_finish"/>
                    </FormGroup>

                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/events">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(EventEdit);