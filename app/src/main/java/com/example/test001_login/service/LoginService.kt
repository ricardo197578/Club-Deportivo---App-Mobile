package com.example.test001_login.service

class LoginService {
    val username : String = "Test";
    val password : String = "1234";

    fun registrarse(usuario : String, contrasenia : String) : String {
        if(usuario == username && password == contrasenia){
            return "Inicio exitoso!";
        }
        return "Error al iniciar";
    }
}