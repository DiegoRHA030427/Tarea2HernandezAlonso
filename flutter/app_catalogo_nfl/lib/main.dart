import 'package:flutter/material.dart';
import 'colores_nfl.dart';
import 'pantallas/draft_contratos.dart';
import 'pantallas/marcador_acciones.dart';
import 'pantallas/ajustes_juego.dart';
import 'pantallas/roster_equipo.dart';
import 'pantallas/resumen_partido.dart';
import 'pantallas/formaciones.dart';
import 'pantallas/mas.dart';

void main() {
  runApp(const CatalogoNFLApp());
}

class CatalogoNFLApp extends StatelessWidget {
  const CatalogoNFLApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'NFL UI Catalog',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        colorSchemeSeed: verdeCampo,
        scaffoldBackgroundColor: blancoLineas,
        appBarTheme: const AppBarTheme(backgroundColor: verdeCampo, foregroundColor: blancoLineas),
      ),
      home: const PantallaPrincipal(),
    );
  }
}

// Las 6 secciones del catálogo. El menú inferior solo admite 5 pestañas, así
// que "Resumen del Partido" y "Formaciones" se abren desde botones en "Más".
enum Seccion { draft, marcador, ajustes, roster, resumen, formaciones, mas }

class PantallaPrincipal extends StatefulWidget {
  const PantallaPrincipal({super.key});

  @override
  State<PantallaPrincipal> createState() => _PantallaPrincipalState();
}

class _PantallaPrincipalState extends State<PantallaPrincipal> {
  Seccion _seccionActual = Seccion.draft;

  static const List<Seccion> _pestanas = [
    Seccion.draft,
    Seccion.marcador,
    Seccion.ajustes,
    Seccion.roster,
    Seccion.mas,
  ];

  void _cambiarSeccion(Seccion nuevaSeccion) {
    setState(() => _seccionActual = nuevaSeccion);
  }

  Widget _construirContenido() {
    switch (_seccionActual) {
      case Seccion.draft:
        return const PantallaDraftContratos();
      case Seccion.marcador:
        return const PantallaMarcadorAcciones();
      case Seccion.ajustes:
        return const PantallaAjustesJuego();
      case Seccion.roster:
        return const PantallaRosterEquipo();
      case Seccion.resumen:
        return const PantallaResumenPartido();
      case Seccion.formaciones:
        return const PantallaFormaciones();
      case Seccion.mas:
        return PantallaMas(
          irAResumen: () => _cambiarSeccion(Seccion.resumen),
          irAFormaciones: () => _cambiarSeccion(Seccion.formaciones),
        );
    }
  }

  int _indiceSeleccionado() {
    if (_seccionActual == Seccion.resumen || _seccionActual == Seccion.formaciones) {
      return _pestanas.indexOf(Seccion.mas);
    }
    return _pestanas.indexOf(_seccionActual);
  }

  @override
  Widget build(BuildContext context) {
    // Las secciones "Resumen" y "Formaciones" traen su propia AppBar (sección 6),
    // así que aquí solo se agrega una barra superior genérica para el resto.
    final necesitaBarraPropia = _seccionActual == Seccion.formaciones;

    return Scaffold(
      appBar: necesitaBarraPropia ? null : AppBar(title: const Text('NFL UI Catalog')),
      body: _construirContenido(),
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
        currentIndex: _indiceSeleccionado(),
        selectedItemColor: verdeCampo,
        onTap: (indice) => _cambiarSeccion(_pestanas[indice]),
        items: const [
          BottomNavigationBarItem(icon: Icon(Icons.sports_football), label: 'Draft'),
          BottomNavigationBarItem(icon: Icon(Icons.flag), label: 'Marcador'),
          BottomNavigationBarItem(icon: Icon(Icons.settings), label: 'Ajustes'),
          BottomNavigationBarItem(icon: Icon(Icons.groups), label: 'Roster'),
          BottomNavigationBarItem(icon: Icon(Icons.more_horiz), label: 'Más'),
        ],
      ),
    );
  }
}
