<template>
  <div class="container">
    <h1>All Subjects</h1>
    <table class="table">
      <thead>
      <tr>
        <th>#</th>
        <th>Name</th>
        <th>Description</th>
        <th>ESP</th>
        <th>Year of study</th>
        <th>Semester</th>
        <th>Action</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(subject, index) in subjects" v-bind:key="subject.subjectId">
        <th scope="row">{{ (currentPage * pageSize) + index + 1 }}</th>
        <td>{{ subject.name }}</td>
        <td>{{ subject.description }}</td>
        <td>{{ subject.esp }}</td>
        <td>{{ subject.yearOfStudy }}</td>
        <td>{{ subject.semester }}</td>
        <td>
          <div v-if="editSubject===subject.subjectId">
            <form class="form-update">
              <div class="form-group row">
                <label for="name" class="col-sm-2 col-form-label">Name</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="name" v-model="subject.name">
                </div>
              </div>
              <div class="form-group row">
                <label for="description" class="col-sm-2 col-form-label">Description</label>
                <div class="col-sm-10">
                  <textarea type="text" class="form-control" id="description" v-model="subject.description"/>
                </div>
              </div>
              <div class="form-group row">
                <label for="esp" class="col-sm-2 col-form-label">ESP</label>
                <div class="col-sm-10">
                  <input type="number" class="form-control" id="esp" v-model="subject.esp">
                </div>
              </div>
              <div class="form-group row">
                <label for="yearOfStudy" class="col-sm-2 col-form-label">Year of study</label>
                <div class="col-sm-10">
                  <input type="number" class="form-control" id="yearOfStudy" v-model="subject.yearOfStudy">
                </div>
              </div>
              <div class="form-group row">
                <label for="semester" class="col-sm-2 col-form-label">Semester</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="semester" v-model="subject.semester">
                </div>
              </div>
            </form>
            <button style="margin:5px" type="button" class="btn btn-success" v-on:click="updateSubject(subject)">
              <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" class="bi bi-check"
                   viewBox="0 0 16 16">
                <path
                    d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
              </svg>
            </button>
            <button class="btn btn-danger" v-on:click="editSubject=null"> X</button>
          </div>
          <button class="btn btn-primary" v-on:click="editSubject=subject.subjectId" style="margin-right: 5px">
            <b-icon-pencil/>
            Edit
          </button>
          <button class="btn btn-danger" v-on:click="deleteSubject(subject)">
            <b-icon-trash/>
            Delete
          </button>
        </td>
      </tr>
      </tbody>
    </table>
    <nav aria-label="Page navigation example">
      <ul class="pagination">
        <li class="page-item"><a class="page-link" @click="gotoToPage(0)">First</a></li>
        <li class="page-item"><a class="page-link" @click="previousPage()">Previous</a></li>
        <li class="page-item" :class="{'active': pageNum===currentPage}" v-for="pageNum of pageLinks" :key="pageNum">
          <a class="page-link" @click="gotoToPage(pageNum)">{{ pageNum }}</a></li>
        <li class="page-item"><a class="page-link" @click="nextPage()">Next</a></li>
        <li class="page-item"><a class="page-link" @click="gotoToPage(totalPage-1)">Last</a></li>
      </ul>
    </nav>
    <div>
      <button class="btn btn-primary" style="display: flex" v-on:click="addSubjectPage">
        <b-icon-plus/>
        Add new
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Subject",
  data() {
    return {
      subjects: [],
      editSubject: null,
      currentPage: 0,
      totalPage: 0,
      pageSize: 3,
      pageLinks: []
    }
  },
  created() {
    this.subjectList();
    this.loadPage();
  },
  methods: {
    subjectList() {
      axios.get("http://localhost:8080/api/subjects")
          .then((response) => {
            this.subjects = response.data
          }).catch((err) => console.log("Error for display subjects" + err))
    },
    updateSubject(subject) {
      fetch("http://localhost:8080/api/subjects/" + subject.subjectId, {
        body: JSON.stringify(subject),
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
      }).then(() => {
        this.editSubject = null;
      })
    },
    async deleteSubject(subject) {
      await axios.delete("http://localhost:8080/api/subjects/" + subject.subjectId);
      this.subjectList();
    },
    addSubjectPage() {
      this.$router.push('/new-subject');
    },
    loadPage() {
      axios.get(`http://localhost:8080/api/subjects/page?page=${this.currentPage}&size=${this.pageSize}`)
          .then((response) => {
            console.log(response)
            this.totalPages = response.data.totalPages;
            this.pageSize = response.data.size;
            this.createPageLinks();
            this.subjectList = response.data.content
          });
    },
    previousPage() {
      this.currentPage = this.currentPage > 0 ? this.currentPage - 1 : 0;
      console.log(this.currentPage)
    },
    nextPage() {
      this.currentPage = this.currentPage < this.totalPages ? this.currentPage + 1 : 0;
      console.log(this.currentPage)
      this.loadPage();
    },
    gotoToPage(page) {
      console.log('page', page);
      this.currentPage = page;
      this.loadPage();
    },
    createPageLinks() {
      const firsPage = this.currentPage === 0 ? this.currentPage : this.currentPage === this.totalPages - 1 ? this.currentPage - 2 : this.currentPage - 1;

      this.pageLinks = [...Array(3).keys()].map(x => firsPage + x);
    }
  }
}
</script>

<style scoped>
.page-item {
  cursor: pointer;
}

.form-group {
  padding: 10px;
}
</style>
