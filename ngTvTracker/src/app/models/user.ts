export class User {
  id: number;
  email: string;
  username: string;
  password: string;
  active: boolean;
  role: string;
  profilePictureUrl: string | null;

  constructor(
    id: number = 0,
    email: string = '',
    username: string = '',
    password: string = '',
    active: boolean = false,
    role: string = '',
    profilePictureUrl: string = ''

    ) {
    this.id = id;
    this.email = email;
    this.username = username;
    this.password = password;
    this.active = active;
    this.role = role;
    this.profilePictureUrl = profilePictureUrl;
  }
}
