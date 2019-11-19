import React from "react";
import Navbar from 'react-bootstrap/Navbar';
import Nav from "react-bootstrap/Nav";
import Button from "react-bootstrap/Button"
import { SkillsLevel } from '@skills/skills-client-react';

import {
  NavLink
} from "react-router-dom";
// import { SkillsLevel } from '@skills/skills-client-vue';

const Navigation = (props) => {
  const projectId = props.projectId;

  return (
    <div>
      <Navbar bg="info" variant="dark">
        <Navbar.Brand>React Integration Examples</Navbar.Brand>
        <Navbar.Toggle target="nav-collapse"></Navbar.Toggle>
        <Navbar.Collapse id="nav-collapse" is-nav="true">
          <Nav>
            <NavLink activeClassName="router-link-active" className="nav-link" to="/">Report Skill Events</NavLink>
            <NavLink activeClassName="router-link-active" className="nav-link" to="/showSkills">User Display</NavLink>
          </Nav>
        </Navbar.Collapse>
          <Button variant="primary">
              <SkillsLevel projectId={projectId}/>
          </Button>
      </Navbar>
    </div>
  );
};

export default Navigation;