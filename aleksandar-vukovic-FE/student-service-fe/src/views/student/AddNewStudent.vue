<template>
  <h1>Add Student</h1>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="firstname">First Name</label>
        <input type="text" class="form-control" id="firstname" required v-model="student.firstname" name="firstname">
      </div>
      <div class="form-group">
        <label for="lastname">Last name</label>
        <input type="text" class="form-control" id="lastname" required v-model="student.lastname" name="lastname"  >
      </div>

      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" id="email" required v-model="student.email" name="email">
      </div>

      <div class="form-group">
        <label for="indexNumber">Index number</label>
        <input type="number" class="form-control" id="indexNumber" required v-model="student.indexNumber" name="indexNumber">
      </div>
      <div class="form-group">
        <label for="indexYear">Index year</label>
        <input type="number" class="form-control" id="indexYear" required v-model="student.indexYear" name="indexYear">
      </div>

      <div class="form-group">
        <label style="margin:15px">City</label>
        <select v-model="student.city">
          <option v-for="city in cities" v-bind:key="city.cityId" :value="city">
            {{city.cityName}}
          </option>
        </select>
      </div>

      <div class="form-group">
        <label for="address">Address</label>
        <input type="text" class="form-control" id="address" required v-model="student.address" name="address">
      </div>

      <div class="form-group">
        <label for="currentYearOfStudy">Current year of study</label>
        <input type="number" class="form-control" id="currentYearOfStudy" required v-model="student.currentYearOfStudy" name="currentYearOfStudy">
      </div>

      <button v-on:click="onSubmit" class="btn btn-success" style="margin-top: 7px; margin-right: 5px;">Submit</button>
      <button v-on:click="cancel" class="btn btn-primary" style="margin-top: 7px">Cancel</button>
    </div>

    <div v-else>
      <h3>You submitted successfully!</h3>
      <button class="btn btn-success" v-on:click="newStudent">Add another student</button>
      <button  @click="goToStudents()" class="btn btn-primary" style="margin-top:12px">List of students</button>

    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "add-student",
  data() {
    return {
      cities:[],
      student: {
        studentId: 0,
        firstname: "",
        lastname: "",
        email: "",
        indexNumber:"",
        indexYear:"",
        city:0,
        address:"",
        currentYearOfStudy:"",
      },

      submitted: false
    };
  },
  created() {
    this.cityList();
  },
  methods: {
    cityList(){
      axios.get("http://localhost:8080/api/cities")
          .then((response) => {
            this.cities = response.data
          }).catch((err) => console.log("Error while displaying cities"+ err))
    },
    onSubmit() {
      var data = {
        studentId: this.student.studentId,
        firstname: this.student.firstname,
        lastname: this.student.lastname,
        email: this.student.email,
        indexNumber: this.student.indexNumber,
        indexYear: this.student.indexYear,
        address: this.student.address,
        city: this.student.city,
        currentYearOfStudy: this.student.currentYearOfStudy
      };

      axios.post("http://localhost:8080/api/students", data)
          .then(response => {
            this.student.studentId = response.data.studentId;
            this.student.city = response.data.city;
            console.log(response.data.studentId);
          })
          .catch(e => {
            console.log(e);
          });

      this.submitted = true;
    },
    cancel(){
      this.$router.push('/students');
    },
    newStudent() {
      this.submitted = false;
      this.student = {};
    },
    goToStudents(){
      this.$router.push('/students');
    }
  }
};
</script>
<style>

select {
  margin: 50px;
  width: 150px;
  padding: 5px 35px 5px 5px;
  font-size: 16px;
  border: 1px solid #CCC;
  height: 34px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background: url(https://www.eng.it/resources/images/logo%20eng.png) 96% / 15% no-repeat;
}
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>