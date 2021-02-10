module.exports = {
    outputDir: "../src/main/resources/static",  
    indexPath: "../static/index.html",
    lintOnSave: false,
    devServer: {  
      proxy: "http://localhost:80"  
    },  
  };