package com.example.a1

import com.example.a1.Ejercicio3.Estudiante
import org.junit.Test
import java.util.Calendar


class Ejercicio1{

    class Computadora(var ram: Int, var memoria: Int, var sistemaOperativo: String, val programasInstalados: List<String>) {
        private var encendida: Boolean = false
        @Test
        fun encender() {
            if (encendida) {
                println("La computadora ya está encendida.")
            } else {
                encendida = true
                println("Computadora encendida.")
            }
        }

        @Test
        fun apagar() {
            if (!encendida) {
                println("La computadora ya está apagada.")
            } else {
                encendida = false
                println("Computadora apagada.")
            }
        }
        @Test
        fun actualizarRam(nuevaRam: Int) {
            ram = nuevaRam
            println("RAM actualizada a $ram GB.")
        }
        @Test
        fun actualizarMemoria(nuevaMemoria: Int) {
            memoria = nuevaMemoria
            println("Memoria actualizada a $memoria GB.")
        }

        fun actualizarSistemaOperativo(nuevoSO: String) {
            sistemaOperativo = nuevoSO
            println("Sistema operativo actualizado a $sistemaOperativo.")
        }

        fun programasDeEsteAnio(): List<String> {
            val anioActual = Calendar.getInstance().get(Calendar.YEAR).toString()
            return programasInstalados.filter { it.contains(anioActual) }
        }
    }
    @Test
    fun main() {
        val programas =
            listOf("Notion 2026", "Facebook 2024", "Spotify 2026", "Chrome 2023", "Discord 2026")

        val pc = Computadora(
            ram = 16,
            memoria = 512,
            sistemaOperativo = "Windows 11",
            programasInstalados = programas
        )

        pc.encender()
        pc.actualizarRam(32)
        pc.actualizarMemoria(1024)
        pc.actualizarSistemaOperativo("Ubuntu 24.04")

        println("\nProgramas instalados de este año: ${pc.programasDeEsteAnio()}")

        pc.apagar()
    }
}

class Ejercicio2{

    class Calculadora(
        val marca: String,
        val aniosDeVida: Int,
        var precio: Double
    ) {
        fun sumar(a: Double, b: Double): Double {
            return a + b
        }

        fun restar(a: Double, b: Double): Double {
            return a - b
        }

        fun multiplicar(a: Double, b: Double): Double {
            return a * b
        }

        fun dividir(a: Double, b: Double): Double {
            if (b == 0.0) {
                println("Error: No se puede dividir entre cero.")
                return 0.0
            }
            return a / b
        }
    }
    @Test
    fun main() {
        val calc = Calculadora(marca = "TexasInstruments", aniosDeVida = 10, precio = 149.99)

        println("Calculadora: ${calc.marca}")
        println("Años de vida: ${calc.aniosDeVida}")
        println("Precio: \$${calc.precio}")

        println("\n10 + 5 = ${calc.sumar(10.0, 5.0)}")
        println("10 - 5 = ${calc.restar(10.0, 5.0)}")
        println("10 * 5 = ${calc.multiplicar(10.0, 5.0)}")
        println("10 / 5 = ${calc.dividir(10.0, 5.0)}")
        println("10 / 0 = ${calc.dividir(10.0, 0.0)}")
        println("La TI es Overkill para sumar")
    }

}

class Ejercicio3{
    class Estudiante(
        val nombre: String,
        val carnet: String,
        val asignatura: String
    )
    @Test
    fun main() {
        val Ciclo01 = listOf(
            Estudiante("Ana García",    "00382922", "Programación de Dispositivos Móviles"),
            Estudiante("Luis Martínez", "00034223", "Programación de Dispositivos Móviles"),
            Estudiante("Sofía López",   "00892324", "Programación de Dispositivos Móviles"),
            Estudiante("Carlos Pérez",  "00382522", "Análisis Numérico"),
            Estudiante("María Ruiz",    "00048321", "Análisis Numérico"),
            Estudiante("Pedro Díaz",    "00283924", "Análisis Numérico"),
            Estudiante("Laura Torres",  "00038520", "Análisis Numérico")
        )

        val estudiantesEnMoviles = Ciclo01.filter {
            it.asignatura == "Programación de Dispositivos Móviles"
        }

        println("Estudiantes en Programación de Dispositivos Móviles:")
        estudiantesEnMoviles.forEach { println("  - ${it.nombre} (${it.carnet})") }
    }

}