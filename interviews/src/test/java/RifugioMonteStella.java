@Test
public void testEffettuaPrenotazione() {
    Rifugio rifugio = new Rifugio();
    Prenotazione prenotazione = new Prenotazione(/* dati di test */);
    assertTrue(rifugio.effettuaPrenotazione(prenotazione));
}

@Test
public void testVerificaDisponibilitaConflitto() {
    Rifugio rifugio = new Rifugio();
    Letto letto = new Letto(/* dati di test */);
    rifugio.aggiungiPrenotazione(new Prenotazione(/* dati sovrapposti */));
    assertFalse(rifugio.verificaDisponibilita(letto, LocalDate.now(), LocalDate.now().plusDays(2)));
}