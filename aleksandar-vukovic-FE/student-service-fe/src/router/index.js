import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Subject from "../views/subject/Subject";
import AddNewSubject from "../views/subject/AddNewSubject";
import Student from "../views/student/Student";
import AddNewStudent from "../views/student/AddNewStudent";
import Professor from "../views/professor/Professor";
import AddNewProfessor from "../views/professor/AddNewProfessor";
import Exam from "../views/exam/Exam";
import AddExam from "../views/exam/AddExam";
import Registration from "../views/registration/Registration";
import AddNewRegistration from "../views/registration/AddNewRegistration";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/subjects',
    name: 'Subjects',
    component: Subject
  },
  {
    path: '/new-subject',
    name: 'AddNewSubject',
    component: AddNewSubject
  },
  {
    path: '/students',
    name: 'Student',
    component: Student
  },
  {
    path: '/new-student',
    name: 'AddNewStudent',
    component: AddNewStudent
  },
  {
    path: '/professors',
    name: 'Professor',
    component: Professor
  },
  {
    path: '/new-professor',
    name: 'AddNewProfessor',
    component: AddNewProfessor
  },
  {
    path: '/exams',
    name: 'Exam',
    component: Exam
  },
  {
    path: '/new-exam',
    name: 'AddExam',
    component: AddExam
  },
  {
    path: '/registration',
    name: 'Registration',
    component: Registration
  },
  {
    path: '/new-registration',
    name: 'AddNewRegistration',
    component: AddNewRegistration
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
