import 'package:flutter/material.dart';
import '../colores_nfl.dart';

// Sección 3: Ajustes del Juego. Checkboxes de clima, RadioButtons de cuarto,
// un Switch, un Slider para la línea de yardaje y un selector de fecha.
class PantallaAjustesJuego extends StatefulWidget {
  const PantallaAjustesJuego({super.key});

  @override
  State<PantallaAjustesJuego> createState() => _PantallaAjustesJuegoState();
}

class _PantallaAjustesJuegoState extends State<PantallaAjustesJuego> {
  bool _climaSoleado = false;
  bool _climaLluvia = false;
  bool _climaNieve = false;

  String _cuartoSeleccionado = '1er Cuarto';
  final List<String> _cuartos = const ['1er Cuarto', '2do Cuarto', '3er Cuarto', '4to Cuarto'];

  bool _transmisionEnVivo = false;
  double _lineaYardaje = 50;

  String _diaPartido = 'Sin seleccionar';

  Future<void> _elegirFecha() async {
    final fechaElegida = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(DateTime.now().year - 1),
      lastDate: DateTime(DateTime.now().year + 1),
    );

    if (fechaElegida != null) {
      setState(() {
        _diaPartido =
            'Día del partido: ${fechaElegida.day.toString().padLeft(2, '0')}/${fechaElegida.month.toString().padLeft(2, '0')}/${fechaElegida.year}';
      });
    }
  }

  void _aplicarAjustes() {
    final clima = [
      if (_climaSoleado) 'Soleado',
      if (_climaLluvia) 'Lluvia',
      if (_climaNieve) 'Nieve',
    ].join(', ');
    final transmision = _transmisionEnVivo ? 'En vivo' : 'Sin transmisión';

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(
          'Ajustes del juego aplicados: ${clima.isEmpty ? 'sin clima' : clima} · $_cuartoSeleccionado · $transmision',
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text(
            'Ajustes del Juego',
            style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
          ),
          const SizedBox(height: 16),
          const Text('Clima del partido', style: TextStyle(fontWeight: FontWeight.bold, color: verdeCampo)),
          CheckboxListTile(
            title: const Text('Soleado'),
            value: _climaSoleado,
            controlAffinity: ListTileControlAffinity.leading,
            onChanged: (valor) => setState(() => _climaSoleado = valor ?? false),
          ),
          CheckboxListTile(
            title: const Text('Lluvia'),
            value: _climaLluvia,
            controlAffinity: ListTileControlAffinity.leading,
            onChanged: (valor) => setState(() => _climaLluvia = valor ?? false),
          ),
          CheckboxListTile(
            title: const Text('Nieve'),
            value: _climaNieve,
            controlAffinity: ListTileControlAffinity.leading,
            onChanged: (valor) => setState(() => _climaNieve = valor ?? false),
          ),
          const SizedBox(height: 8),
          const Text('Cuarto actual', style: TextStyle(fontWeight: FontWeight.bold, color: verdeCampo)),
          ..._cuartos.map(
            (cuarto) => RadioListTile<String>(
              title: Text(cuarto),
              value: cuarto,
              groupValue: _cuartoSeleccionado,
              onChanged: (valor) => setState(() => _cuartoSeleccionado = valor!),
            ),
          ),
          const SizedBox(height: 8),
          SwitchListTile(
            title: const Text('Transmisión en vivo'),
            value: _transmisionEnVivo,
            onChanged: (valor) => setState(() => _transmisionEnVivo = valor),
          ),
          const SizedBox(height: 8),
          Text(
            'Línea de yardaje: ${_lineaYardaje.toInt()} yardas',
            style: const TextStyle(fontWeight: FontWeight.bold, color: verdeCampo),
          ),
          Slider(
            value: _lineaYardaje,
            min: 0,
            max: 100,
            divisions: 100,
            label: '${_lineaYardaje.toInt()}',
            onChanged: (valor) => setState(() => _lineaYardaje = valor),
          ),
          const SizedBox(height: 8),
          const Text('Día del partido', style: TextStyle(fontWeight: FontWeight.bold, color: verdeCampo)),
          Padding(
            padding: const EdgeInsets.symmetric(vertical: 8),
            child: Text(_diaPartido),
          ),
          OutlinedButton(
            onPressed: _elegirFecha,
            child: const Text('Elegir fecha'),
          ),
          const SizedBox(height: 24),
          SizedBox(
            width: double.infinity,
            child: ElevatedButton(
              style: ElevatedButton.styleFrom(backgroundColor: azulMarinoEquipo, foregroundColor: blancoLineas),
              onPressed: _aplicarAjustes,
              child: const Text('Aplicar Ajustes'),
            ),
          ),
        ],
      ),
    );
  }
}
