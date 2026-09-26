import 'package:flutter/material.dart';
import '../colores_nfl.dart';
import '../datos_globales.dart';

// Sección 1: Draft y Contratos. Valida el nombre y el número de camiseta
// (con el "pañuelo amarillo" si están mal) y, si todo es correcto, guarda al
// jugador en la lista global jugadoresDraft para que la Sección 4 lo muestre.
class PantallaDraftContratos extends StatefulWidget {
  const PantallaDraftContratos({super.key});

  @override
  State<PantallaDraftContratos> createState() => _PantallaDraftContratosState();
}

class _PantallaDraftContratosState extends State<PantallaDraftContratos> {
  final _controladorNombre = TextEditingController();
  final _controladorNumero = TextEditingController();
  final _controladorContrasena = TextEditingController();
  final _controladorSalario = TextEditingController();

  String? _errorNombre;
  String? _errorNumero;

  void _reclutarJugador() {
    final nombre = _controladorNombre.text.trim();
    final numero = int.tryParse(_controladorNumero.text.trim());

    setState(() {
      _errorNombre = nombre.isEmpty ? '🚩 Pañuelo amarillo: el nombre es obligatorio' : null;
      _errorNumero = (numero == null || numero < 1 || numero > 99)
          ? '🚩 Pañuelo amarillo: número inválido (1-99)'
          : null;
    });

    if (nombre.isNotEmpty && numero != null && numero >= 1 && numero <= 99) {
      setState(() {
        jugadoresDraft.add('#$numero $nombre');
        _controladorNombre.clear();
        _controladorNumero.clear();
        _controladorContrasena.clear();
        _controladorSalario.clear();
      });

      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('$nombre fue reclutado al equipo')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          const Text(
            'Draft y Contratos',
            style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
            textAlign: TextAlign.center,
          ),
          const SizedBox(height: 16),
          TextField(
            controller: _controladorNombre,
            decoration: InputDecoration(
              labelText: 'Nombre del jugador',
              errorText: _errorNombre,
              border: const OutlineInputBorder(),
            ),
          ),
          const SizedBox(height: 12),
          TextField(
            controller: _controladorNumero,
            keyboardType: TextInputType.number,
            decoration: InputDecoration(
              labelText: 'Número de camiseta (1-99)',
              errorText: _errorNumero,
              border: const OutlineInputBorder(),
            ),
          ),
          const SizedBox(height: 12),
          TextField(
            controller: _controladorContrasena,
            obscureText: true,
            decoration: const InputDecoration(
              labelText: 'Contraseña',
              border: OutlineInputBorder(),
            ),
          ),
          const SizedBox(height: 12),
          TextField(
            controller: _controladorSalario,
            keyboardType: TextInputType.number,
            decoration: const InputDecoration(
              labelText: 'Salario anual (\$)',
              border: OutlineInputBorder(),
            ),
          ),
          const SizedBox(height: 20),
          ElevatedButton(
            style: ElevatedButton.styleFrom(
              backgroundColor: doradoEquipo,
              foregroundColor: negroArbitro,
              padding: const EdgeInsets.symmetric(vertical: 14),
            ),
            onPressed: _reclutarJugador,
            child: const Text('Seleccionar Jugador (Draft)'),
          ),
        ],
      ),
    );
  }
}
