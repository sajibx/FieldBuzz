# FieldBuzz



<h1 align="center">
    Assignment Documentation
</h1>

<div >
       <img align="top" src="https://raw.githubusercontent.com/sajibx/FieldBuzz/main/images/Screenshot_1.png" width="300">
      <p align="left"><b>1.</b> The login screen, part of authorisation process, requires user email and password</p>
  <br>
</div>

<div >
       <img align="top" src="https://raw.githubusercontent.com/sajibx/FieldBuzz/main/images/Screenshot_2.png" width="300">
      <p align="left">User information form fields</p>
  <br>
</div>

<div >
       <img align="top" src="https://raw.githubusercontent.com/sajibx/FieldBuzz/main/images/Screenshot_3.png" width="300">
      <p align="left">User information form fields with file select and submit option</p>
  <br>
</div>

| <h3 align="center">Function Name</h3>|                                       <h3 align="center">Explanation</h3>                                                                           |
| :---                      |                                                                                                                                             ----: |
| <b>init()| <p align="left">Varifies user login states and directs them to login or home page ( MainActivity() )</p>   
| <b>init()| <p align="left">Contains login procedure, user authentication, token store and handling ( LoginFragment() )</p>   
| <b>init()</b>   | <p align="left">Contains all form validation mention in fieldbuzz ‘s documentation <b>section 4.2.1.1.</b> Also contains logout event handling ( HomeFragment() )</p>                                |
| <b>submit()</b> | <p align="left">Contains all user provided data varification, manupulation, locally storing user information, uuid genarate, user information post and CV post initiation ( HomeFragment() )</p>    |
| <b>resume()</b>  | <p align="left">Contains CV pdf file initiation and posting ( HomeFragment() )</p> |
                           

<p align="center">Application information</p>

<div>
      <p align="left"><b>1.</b> After user login, the responce token is stored in shared preference and later used for API calls and user login state varification</b>.</p>
</div>

<div>
      <p align="left"><b>2.</b> <b>tysnic_id</b>  is genarated based on user provided email, thus it is always unique. <b>tysnic_id</b>  is provided with initial form data post with user provided information </b>.</p>
</div>

<p align="left"><b>3.</b> <b>id</b> from CV object recived from user form post is used to post user CV </p>
</div>

<p align="left"><b>4.</b> <b>tysnic_id</b> and <b>id</b> is used to post user information and CV as well as to update user information and CV </p>
</div>


<p align='justify'>Below are the library information used in the application</p>
    <ul>
        <li>     <b>Fuel & GSON</b> - Network and json data library</li>
        <li>     <b>Anko & Coroutine</b> - Threading library for task handling </li>
        <li>     <b>Dock Picker</b> - Pdf file picker library </li>
        <li>     <b>Realm</b> - Local storage for user information storing and verifying </li>
    </ul>
    
