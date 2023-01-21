import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {TokenStorageService} from "../../services/token-storage.service";
import {NotificationService} from "../../services/notification.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {
  public registerForm: FormGroup;

  constructor(
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private notificationService: NotificationService,
    private router: Router,
    private fb: FormBuilder) {

  }

  ngOnInit(): void {
    this.registerForm = this.createRegisterForm();
  }

  createRegisterForm(): FormGroup {
    return this.fb.group({
      username: ['', Validators.compose([Validators.required])],
      password: ['', Validators.compose([Validators.required])],
      firstname: ['', Validators.compose([Validators.required])],
      lastname: ['', Validators.compose([Validators.required])],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      confirmPassword: ['', Validators.compose([Validators.required, this.passValidator])]
    })
  }

  passValidator(): ValidatorFn {
    return (): ValidationErrors | null => {
      const control1 = this.registerForm.controls['password'];
      const control2 = this.registerForm.controls['confirmPassword'];
      const validation = control1.value !== control2.value;
      if (validation) {
        this.notificationService.showSnackBar("Password isn't same!")
        return {notEquivalent: true};
      } else return null;
    }
  }

  register(): void {
    this.authService.register({
      username: this.registerForm.value.username,
      password: this.registerForm.value.password,
      email: this.registerForm.value.email,
      firstname: this.registerForm.value.firstname,
      lastname: this.registerForm.value.lastname,
      confirmPassword: this.registerForm.value.confirmPassword
    }).subscribe(data => {
      console.log(data);

      this.tokenStorage.save(data.token);
      this.tokenStorage.saveUser(data);

      this.notificationService.showSnackBar('Success, user is registered');
      this.router.navigate(['/']);
      window.location.reload();
    }, error => {
      console.log(error);
      this.notificationService.showSnackBar(error.message);
    });
  }

  login():void {
    this.router.navigate(['/login']);
  }

}
