import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {TokenStorageService} from "../../services/token-storage.service";
import {NotificationService} from "../../services/notification.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm: FormGroup;

  constructor(
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private notificationService: NotificationService,
    private router: Router,
    private fb: FormBuilder) {

    // if (this.tokenStorage.getToken()) {
    //   this.router.navigate(['/main']);
    // }
    this.loginForm = this.createLoginForm();
  }

  ngOnInit(): void {
    this.loginForm = this.createLoginForm();
  }
  createLoginForm(): FormGroup {
    return this.fb.group({
      username: ['', Validators.compose([Validators.required])],
      password: ['', Validators.compose([Validators.required])],
    })
  }

  submit(): void {
    this.authService.login({
      username: this.loginForm.value.username,
      password: this.loginForm.value.password
    }).subscribe(isValid => {
      console.log(isValid);
      if (isValid) {
        // sessionStorage.setItem(
        //   'token',
        //   btoa(this.loginForm.value.username + ':' + this.loginForm.value.password)
        // );
        this.notificationService.showSnackBar('Success');
        //this.router.navigate(['main'])
      } else {
        this.notificationService.showSnackBar("Authentication failed.");
        alert()
      }
    });
  }

  register():void {
    this.router.navigate(['/register']);
  }
}
