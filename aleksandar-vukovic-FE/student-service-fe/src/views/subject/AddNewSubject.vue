<template>
  <h1>Add subject</h1>
  <div class="submit-form">
    <div v-if="!submitted">

      <div class="form-group">
        <label for="name">Name</label>
        <input
            type="text"
            class="form-control"
            id="name"
            required
            v-model="subject.name"
            name="name"
        />
      </div>

      <div class="form-group">
        <label for="description">Description</label>
        <textarea
            class="form-control"
            id="description"
            required
            v-model="subject.description"
            name="description"
        />
      </div>

      <div class="form-group">
        <label for="esp">ESP</label>
        <input
            class="form-control"
            id="esp"
            required
            v-model="subject.esp"
            name="esp"
        />
      </div>

      <div class="form-group">
        <label for="yearOfStudy">Year of study</label>
        <input
            class="form-control"
            id="yearOfStudy"
            required
            v-model="subject.yearOfStudy"
            name="yearOfStudy"
        />
      </div>

      <div class="form-group">
        <label for="semester">Semester</label>
        <input
            class="form-control"
            id="semester"
            required
            v-model="subject.semester"
            name="semester"
        />
      </div>
      <button v-on:click="onSubmit" class="btn btn-success" style="margin-top: 7px; margin-right: 5px;">Submit</button>
      <button v-on:click="cancel" class="btn btn-primary" style="margin-top: 7px">Cancel</button>
    </div>
    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" v-on:click="newSubject">Add another subject</button>
      <button  @click="backToSubjects()" class="btn btn-primary" style="margin-top:12px">Back to subjects</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
name: "AddNewSubject",
  data() {
    return {
      subject: {
        subjectId: 0,
        name: "",
        description: "",
        esp: "",
        yearOfStudy: "",
        semester: ""
      },
      submitted: false
    };
  },
  methods: {
    onSubmit(){
      var formData = {
        subjectId: this.subject.subjectId,
        name: this.subject.name,
        description: this.subject.description,
        esp: this.subject.esp,
        yearOfStudy: this.subject.yearOfStudy,
        semester: this.subject.semester,
      }

      axios.post("http://localhost:8080/api/subjects", formData)
          .then(response => {
            this.subject.subjectId = response.data.subjectId;
            console.log(response.formData.subjectId)
          }).catch(error => console.log(error));
      this.submitted = true;
    },
    cancel(){
      this.$router.push('/subjects');
    },
    newSubject() {
      this.submitted = false;
      this.subject = {
        subjectId: 0
      };
    },
    backToSubjects(){
      this.$router.push('/subjects');
    },

  }
}
</script>

<style scoped>
.submit-form{
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
}
</style>