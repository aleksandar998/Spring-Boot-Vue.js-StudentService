<template>
  <div class="container">
    <h1>All Students</h1>
    <table class="table">
      <thead>
      <tr>
        <th>#</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Email</th>
        <th>Index number</th>
        <th>Index year</th>
        <th>City</th>
        <th>Address</th>
        <th>Current year of study</th>
        <th>Action</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(student, index) in students" v-bind:key="student.studentId">
        <th scope="row">{{(currentPage * pageSize) + index + 1}}</th>
        <td>{{student.firstname}}</td>
        <td>{{student.lastname}}</td>
        <td>{{student.email}}</td>
        <td>{{student.indexNumber}}</td>
        <td>{{student.indexYear}}</td>
        <td>{{student.city.cityName}}</td>
        <td>{{student.address}}</td>
        <td>{{student.currentYearOfStudy}}</td>
        <td>
          <div  v-if="editStudent===student.studentId">
            <form class="form-update">
              <div class="form-group row">
                <label for="firstname" class="col-sm-2 col-form-label">First name</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="firstname" v-model="student.firstname">
                </div>
              </div>
              <div class="form-group row">
                <label for="lastname" class="col-sm-2 col-form-label">Last name</label>
                <div class="col-sm-10">
                  <textarea type="text" class="form-control" id="lastname" v-model="student.lastname"/>
                </div>
              </div>
              <div class="form-group row">
                <label for="email" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                  <input type="email" class="form-control" id="email" v-model="student.email">
                </div>
              </div>
              <div class="form-group row">
                <label for="indexNumber" class="col-sm-2 col-form-label">Index number</label>
                <div class="col-sm-10">
                  <input type="number" class="form-control" id="indexNumber" v-model="student.indexNumber">
                </div>
              </div>
              <div class="form-group row">
                <label for="indexYear" class="col-sm-2 col-form-label">Index year</label>
                <div class="col-sm-10">
                  <input type="number" class="form-control" id="indexYear" v-model="student.indexYear">
                </div>
              </div>
              <div class="form-group row">
                <label for="city" class="col-sm-2 col-form-label">City</label>
                <div class="col-sm-10">
                  <select v-model="student.city">
                    <option v-for="city in cities" v-bind:key="city.cityId" :value="city" id="city">
                      {{city.cityName}}
                    </option>
                  </select>
                </div>
              </div>
              <div class="form-group row">
                <label for="address" class="col-sm-2 col-form-label">Address</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="address" v-model="student.address">
                </div>
              </div>
              <div class="form-group row">
                <label for="currentYearOfStudy" class="col-sm-2 col-form-label">Current year of study</label>
                <div class="col-sm-10">
                  <input type="number" class="form-control" id="currentYearOfStudy" v-model="student.currentYearOfStudy">
                </div>
              </div>
            </form>
            <button style="margin:5px" type="button" class="btn btn-success" v-on:click="updateStudent(student)">
              <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
              </svg>
            </button>
            <button class="btn btn-danger" v-on:click="editStudent=null"> X </button>
          </div>
          <button class="btn btn-primary" v-on:click="editStudent=student.studentId" style="margin-right: 5px"><b-icon-pencil/> Edit</button>
          <button class="btn btn-danger" v-on:click="deleteStudent(student)"><b-icon-trash/> Delete</button>
        </td>
      </tr>
      </tbody>
    </table>
    <nav aria-label="Page navigation example">
      <ul class="pagination">
        <li class="page-item"><a class="page-link" @click="gotoToPage(0)">First</a></li>
        <li class="page-item"><a class="page-link" @click="previousPage()">Previous</a></li>
        <li class="page-item" :class="{'active': pageNum===currentPage}" v-for="pageNum of pageLinks" :key="pageNum">
          <a class="page-link"  @click="gotoToPage(pageNum)" >{{pageNum}}</a></li>
        <li class="page-item"><a class="page-link" @click="nextPage()">Next</a></li>
        <li class="page-item"><a class="page-link" @click="gotoToPage(totalPage-1)">Last</a></li>
      </ul>
    </nav>
    <div class="buttons">
      <button class="btn btn-primary" v-on:click="addStudentPage" style="margin-right: 950px;"><b-icon-plus/> Add new</button>
      <button class="btn btn-primary" v-on:click="addRegistration"><b-icon-plus/> Add new registration</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Student",
  data(){
    return{
      cities: [],
      students: [],
      editStudent: null,
      currentPage: 0,
      totalPage: 0,
      pageSize: 3,
      pageLinks: []
    }
  },
  created() {
    this.studentList();
    this.cityList();
    this.loadPage();
  },
  methods: {
    studentList(){
      axios.get("http://localhost:8080/api/students")
          .then((response) => {
            this.students = response.data
          }).catch((err) => console.log("Error for display students"+ err))
    },
    cityList(){
      axios.get("http://localhost:8080/api/cities")
          .then(response => {
            this.cities = response.data
          }).catch((err) => console.log("Error for display cities"+ err))
    },
    updateStudent(student) {
      fetch("http://localhost:8080/api/students/" + student.studentId, {
        body: JSON.stringify(student),
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
      }).then(() => {
        this.editStudent = null;
      })
    },
    async deleteStudent(student){
      await axios.delete("http://localhost:8080/api/students/" + student.studentId);
      this.studentList();
    },
    addStudentPage(){
      this.$router.push('/new-student');
    },
    addRegistration(){
      this.$router.push('/new-registration');
    },
    loadPage(){
      axios.get(`http://localhost:8080/api/students/page?page=${this.currentPage}&size=${this.pageSize}`)
          .then((response) => {
            console.log(response)
            this.totalPages = response.data.totalPages;
            this.pageSize = response.data.size;
            this.createPageLinks();
            this.studentList = response.data.content
          });
    },
    previousPage(){
      this.currentPage = this.currentPage > 0 ? this.currentPage - 1 : 0;
      console.log(this.currentPage)
    },
    nextPage() {
      this.currentPage = this.currentPage < this.totalPages ? this.currentPage + 1 : 0;
      console.log(this.currentPage)
      this.loadPage();
    },
    gotoToPage(page) {
      console.log('page',page);
      this.currentPage = page;
      this.loadPage();
    },
    createPageLinks() {
      const firsPage = this.currentPage ===0 ? this.currentPage : this.currentPage ===this.totalPages - 1?this.currentPage -2 : this.currentPage -1 ;

      this.pageLinks = [...Array(3).keys()].map(x => firsPage + x);
    }
  }
}
</script>

<style scoped>
.form-group {
  padding: 10px;
}
.buttons{
  display: flex;
}
</style>