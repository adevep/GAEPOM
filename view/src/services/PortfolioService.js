import http from "../http-common";

class PortfolioService {
  getAll() {
    return http.get("/portfolio");
  }

  get(pfSeq) {
    return http.get(`/portfolio/${pfSeq}`);
  }

  create(data) {
    return http.post("/portfolio", data);
  }

  update(pfSeq, data) {
    return http.put(`/portfolio/${pfSeq}`, data);
  }

  delete(pfSeq) {
    return http.delete(`/portfolio/${pfSeq}`);
  }

  deleteAll() {
    return http.delete(`/portfolio`);
  }

  findPortfolioByUserId(userId) {
    return http.get(`/portfolio?userId=${userId}`);
  }
  
  findByPfSubtitle(pfSubtitle) {
    return http.get(`/portfolio?pfSubtitle=${pfSubtitle}`);
  }
}

export default new PortfolioService();