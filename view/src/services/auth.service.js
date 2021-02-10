import axios from 'axios';

const API_URL = 'http://localhost:80/api/auth/';

/* eslint class-methods-use-this: ["error", { "exceptMethods": ["login", "logout", "register"] }] */
class AuthService {
  login(user) {
    return axios
      .post(`${API_URL}signin`, {
        userId: user.userId,
        password: user.password,
      })
      .then((response) => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(`${API_URL}signup`, {
      userId: user.userId,
      email: user.email,
      password: user.password,
    });
  }
}

export default new AuthService();
