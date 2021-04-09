<template>
  <div class="container">
    <h1>All Professors</h1>
    <table class="table">
      <thead>
      <tr>
        <th>#</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Email</th>
        <th>City</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Reelection date</th>
        <th>Title</th>
        <th>Action</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(professor, index) in professors" v-bind:key="professor.professorId">
        <th scope="row">{{(currentPage * pageSize) + index + 1}}</th>
        <td>{{professor.firstname}}</td>
        <td>{{professor.lastname}}</td>
        <td>{{professor.email}}</td>
        <td>{{professor.city.cityName}}</td>
        <td>{{professor.address}}</td>
        <td>{{professor.phone}}</td>
        <td>{{professor.reelectionDate}}</td>
        <td>{{professor.title.titleName}}</td>
        <td>
          <div  v-if="editProfessor===professor.professorId">
            <form class="form-update">
              <div class="form-group row">
                <label for="firstname" class="col-sm-2 col-form-label">First name</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="firstname" v-model="professor.firstname">
                </div>
              </div>
              <div class="form-group row">
                <label for="lastname" class="col-sm-2 col-form-label">Last name</label>
                <div class="col-sm-10">
                  <textarea type="text" class="form-control" id="lastname" v-model="professor.lastname"/>
                </div>
              </div>
              <div class="form-group row">
                <label for="email" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                  <input type="email" class="form-control" id="email" v-model="professor.email">
                </div>
              </div>
              <div class="form-group row">
                <label for="city" class="col-sm-2 col-form-label">City</label>
                <div class="col-sm-10">
                  <select v-model="professor.city">
                    <option v-for="city in cities" v-bind:key="city.cityId" :value="city" id="city">
                      {{city.cityName}}
                    </option>
                  </select>
                </div>
              </div>
              <div class="form-group row">
                <label for="address" class="col-sm-2 col-form-label">Address</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="address" v-model="professor.address">
                </div>
              </div>
              <div class="form-group row">
                <label for="phone" class="col-sm-2 col-form-label">Phone</label>
                <div class="col-sm-10">
                  <input type="tel" class="form-control" id="phone" v-model="professor.phone">
                </div>
              </div>
              <div class="form-group row">
                <label for="reelectionDate" class="col-sm-2 col-form-label">Reelection date</label>
                <div class="col-sm-10">
                  <input type="date" class="form-control" id="reelectionDate" v-model="professor.reelectionDate">
                </div>
              </div>
              <div class="form-group row">
                <label for="title" class="col-sm-2 col-form-label">Title</label>
                <div class="col-sm-10">
                  <select v-model="professor.title">
                    <option v-for="title in titles" v-bind:key="title.titleId" :value="title" id="title">
                      {{title.titleName}}
                    </option>
                  </select>
                </div>
              </div>
            </form>
            <button style="margin:5px" type="button" class="btn btn-success" v-on:click="updateProfessor(professor)">
              <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
              </svg>
            </button>
            <button class="btn btn-danger" v-on:click="editProfessor=null"> X </button>
          </div>
          <button class="btn btn-primary" v-on:click="editProfessor=professor.professorId" style="margin-right: 5px"><b-icon-pencil/> Edit</button>
          <button class="btn btn-danger" v-on:click="deleteProfessor(professor)"><b-icon-trash/> Delete</button>
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
    <div>
      <button class="btn btn-primary" v-on:click="addProfessorPage" style="display: flex"><b-icon-plus/> Add new</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
name: "Professor",
  data(){
    return{
      cities: [],
      titles: [],
      professors: [],
      editProfessor: null,
      currentPage: 0,
      totalPage: 0,
      pageSize: 3,
      pageLinks: []
    }
  },
  created() {
    this.professorList();
    this.cityList();
    this.titleList();
    this.loadPage();
  },
  methods: {
    professorList(){
      axios.get("http://localhost:8080/api/professors")
          .then((response) => {
            this.professors = response.data
          }).catch((err) => console.log("Error for display professors"+ err))
    },
    cityList(){
      axios.get("http://localhost:8080/api/cities")
          .then((response) => {
            this.cities = response.data
          }).catch((err) => console.log("Error for display cities"+ err))
    },
    titleList(){
      axios.get("http://localhost:8080/api/titles")
          .then((response) => {
            this.titles = response.data
          }).catch((err) => console.log("Error for display titles"+ err))
    },
    updateProfessor(professor) {
      fetch("http://localhost:8080/api/professors/" + professor.professorId, {
        body: JSON.stringify(professor),
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
      }).then(() => {
        this.editProfessor = null;
      })
    },
    async deleteProfessor(professor){
      await axios.delete("http://localhost:8080/api/professors/" + professor.professorId);
      this.professorList();
    },
    addProfessorPage(){
      this.$router.push('/new-professor');
    },
    loadPage(){
      axios.get(`http://localhost:8080/api/professors/page?page=${this.currentPage}&size=${this.pageSize}`)
          .then((response) => {
            console.log(response)
            this.totalPages = response.data.totalPages;
            this.pageSize = response.data.size;
            this.createPageLinks();
            this.professorList = response.data.content
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

</style>