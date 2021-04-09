<template>
  <h1>Add Professor</h1>
    <div class="submit-form">
      <div v-if="!submitted">
        <div class="form-group">
          <label for="firstname">First name</label>
          <input type="text" class="form-control" id="firstname" required v-model="professor.firstname" name="firstname">
        </div>
        <div class="form-group">
          <label for="lastname">Last name</label>
          <input type="text" class="form-control" id="lastname" required v-model="professor.lastname" name="lastname"  >
        </div>

        <div class="form-group">
          <label for="email">Email</label>
          <input type="text" class="form-control" id="email" required v-model="professor.email" name="email">
        </div>

        <div class="form-group">
          <label for="address">Address</label>
          <input type="text" class="form-control" id="address" required v-model="professor.address" name="address">
        </div>
        <div class="form-group">
          <label for="phone">Phone</label>
          <input type="text" class="form-control" id="phone" required v-model="professor.phone" name="phone">
        </div>
        <div class="form-group">
          <label for="reelectionDate">Reelection date</label>
          <input type="date" class="form-control" id="reelectionDate" required v-model="professor.reelectionDate" name="reelectionDate">
        </div>


        <div class="form-group">
          <label style="margin:15px">City</label>
          <select v-model="professor.city">
            <option v-for="city in cities" v-bind:key="city.cityId" :value="city">
              {{city.cityName}}
            </option>
          </select>
        </div>


        <div class="form-group">
          <label style="margin:15px">Title</label>
          <select v-model="professor.title">
            <option v-for="title in titles" v-bind:key="title.titleId" :value="title">
              {{title.titleName}}
              <i class="arrow down"/>
            </option>
          </select>
        </div>
        <button v-on:click="onSubmit" class="btn btn-success" style="margin-top: 7px; margin-right: 5px;">Submit</button>
        <button v-on:click="cancel" class="btn btn-primary" style="margin-top: 7px">Cancel</button>
      </div>

      <div v-else>
        <h3>You submitted successfully!</h3>
        <button class="btn btn-success" v-on:click="newProfessor">Add another professor</button>
        <button  @click="backToProfessors()" class="btn btn-primary" style="margin-top:12px">Back to professors</button>
      </div>
    </div>
</template>


<script>
import axios from "axios";
export default {
  name: "add-professor",
  data() {
    return {
      titles:[],
      cities:[],
      professor: {
        professorId: 0,
        firstname: "",
        lastname: "",
        email: "",
        address:"",
        phone:"",
        reelectionDate:"",
        title:0,
        city:0,
      },
      submitted: false
    };
  },
  created() {
    this.cityList();
    this.titleList();
  },
  methods: {
    cityList(){
      axios.get("http://localhost:8080/api/cities")
          .then((response) => {
            this.cities = response.data
          }).catch((err) => console.log("Error while displaying cities"+ err))
    },
    titleList(){
      axios.get("http://localhost:8080/api/titles")
          .then((response) => {
            this.titles = response.data
          }).catch((err) => console.log("Error while displaying titles"+ err))
    },
    onSubmit() {
      var data = {
        professorId: this.professor.professorId,
        firstname: this.professor.firstname,
        lastname: this.professor.lastname,
        email: this.professor.email,
        address: this.professor.address,
        phone:this.professor.phone,
        reelectionDate:this.professor.reelectionDate,
        city: this.professor.city,
        title:this.professor.title
      };

      axios.post("http://localhost:8080/api/professors", data)
          .then(response => {
            this.professor.professorId = response.data.professorId;
            this.professor.city = response.data.city;
            this.professor.title = response.data.title;
            console.log(response.data.professorId);
          })
          .catch(e => {
            console.log(e);
          });

      this.submitted = true;
    },
    cancel(){
      this.$router.push('/professors');
    },
    newProfessor() {
      this.submitted = false;
      this.professor = {};
    },
    backToProfessors(){
      this.$router.push('/professors');
    }
  }
};
</script>

<style scoped>
select {
  margin: 20px;
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