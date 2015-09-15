/*     */ package br.com.schneider.sgm.dispositivos;
/*     */ 
/*     */ import br.com.schneider.sgm.eventos.UPSListener;
/*     */ import br.com.schneider.sgm.protocolo.ProtocoloUPS;
/*     */ import br.com.schneider.sgm.ups.AbstractUPS;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Rhino
/*     */   extends AbstractUPS
/*     */ {
/*     */   private float potenciaAparenteEntrada;
/*     */   private float potenciaAparenteSaida;
/*     */   private float potenciaRealEntrada;
/*     */   private float potenciaRealSaida;
/*     */   protected float frequenciaEntradaC;
/*     */   protected float tensaoSaida220F1MB;
/*     */   protected float tensaoSaida220F2MB;
/*     */   protected float tensaoSaida220F1MR;
/*     */   protected float tensaoSaida220F2MR;
/*     */   protected float tensaoSaida110F1MB;
/*     */   protected float tensaoSaida110F2MB;
/*     */   protected float tensaoSaida110F1MR;
/*     */   protected float tensaoSaida110F2MR;
/*     */   protected float tensaoEntradaOffset;
/*     */   protected int comparadorTensaoEntrada;
/*     */   protected float tensaoEntradaF1193;
/*     */   protected float tensaoEntradaF2193;
/*     */   protected float tensaoEntradaF1194;
/*     */   protected float tensaoEntradaF2194;
/*     */   protected float tensaoBateriaF1;
/*     */   protected float tensaoBateriaF2;
/*     */   protected float correnteSaida220F1MB;
/*     */   protected float correnteSaida220F2MB;
/*     */   protected float correnteSaida220F1MR;
/*     */   protected float correnteSaida220F2MR;
/*     */   protected float correnteSaida110F1MB;
/*     */   protected float correnteSaida110F2MB;
/*     */   protected float correnteSaida110F1MR;
/*     */   protected float correnteSaida110F2MR;
/*     */   protected float potenciaAparenteOffset;
/*     */   protected float potenciaAparente220F1MR;
/*     */   protected float potenciaAparente220F2MR;
/*     */   protected float potenciaAparente220F1MB;
/*     */   protected float potenciaAparente220F2MB;
/*     */   protected float potenciaAparente110F1MR;
/*     */   protected float potenciaAparente110F2MR;
/*     */   protected float potenciaAparente110F1MB;
/*     */   protected float potenciaAparente110F2MB;
/*     */   protected float potenciaReal220F1MR;
/*     */   protected float potenciaReal220F2MR;
/*     */   protected float potenciaReal220F1MB;
/*     */   protected float potenciaReal220F2MB;
/*     */   protected float potenciaReal110F1MR;
/*     */   protected float potenciaReal110F2MR;
/*     */   protected float potenciaReal110F1MB;
/*     */   protected float potenciaReal110F2MB;
/*     */   protected float correnteEntradaF1MR;
/*     */   protected float correnteEntradaF2MR;
/*     */   protected float bateriaMaximoMR;
/*     */   protected float bateriaMinimoMR;
/*     */   protected float bateriaMaximoMB;
/*     */   protected float bateriaMinimoMB;
/*     */   protected int comparadorAutonomiaBateria;
/*     */   protected int comparadorContadorAutonomiaBaixa;
/* 114 */   protected int[] calcAut0100 = { 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 10, 11, 12, 15, 17, 19, 22, 25, 28, 31, 35, 40, 43, 47, 52, 59, 64, 68, 75 };
/*     */   
/* 116 */   protected int[] calcAut0200 = { 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 18, 20, 21, 24, 28, 30, 32, 33, 33, 34 };
/*     */   
/* 118 */   protected int[] calcAut0300 = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 17, 19, 21, 22, 23 };
/*     */   
/* 120 */   protected int[] calcAut0400 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 6, 7, 7, 8, 9, 19, 11, 12, 13, 14, 16, 18, 20 };
/*     */   
/* 122 */   protected int[] calcAut0500 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 10, 11, 12, 13, 14 };
/*     */   
/* 124 */   protected int[] calcAut0600 = { 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 6, 6, 6, 7, 7, 8, 9, 10, 11, 12, 12, 12 };
/*     */   
/* 126 */   protected int[] calcAut0700 = { 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 9, 10, 10, 10, 10, 10, 10 };
/*     */   
/*     */ 
/*     */   public Rhino(ProtocoloUPS protocolo)
/*     */   {
/* 131 */     boolean[] dias = new boolean[7];
/* 132 */     setProtocoloUPS(protocolo);
/* 133 */     instanciaGlobais();
/* 134 */     setDiasSemanaProgramados(dias);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void instanciaGlobais()
/*     */   {
/* 143 */     this.frequenciaEntradaC = 101715.0F;
/* 144 */     this.tensaoSaida220F1MB = 3.15F;
/* 145 */     this.tensaoSaida220F2MB = 2.0F;
/* 146 */     this.tensaoSaida220F1MR = 2.9F;
/* 147 */     this.tensaoSaida220F2MR = 13.0F;
/* 148 */     this.tensaoSaida110F1MB = 1.4F;
/* 149 */     this.tensaoSaida110F2MB = 18.0F;
/* 150 */     this.tensaoSaida110F1MR = 1.44F;
/* 151 */     this.tensaoSaida110F2MR = 13.0F;
/* 152 */     this.tensaoEntradaOffset = 30.0F;
/* 153 */     this.comparadorTensaoEntrada = 194;
/* 154 */     this.tensaoEntradaF1193 = 1.141F;
/* 155 */     this.tensaoEntradaF2193 = 12.0F;
/* 156 */     this.tensaoEntradaF1194 = 2.5F;
/* 157 */     this.tensaoEntradaF2194 = -250.0F;
/* 158 */     this.tensaoBateriaF1 = 0.13908206F;
/* 159 */     this.tensaoBateriaF2 = 0.6F;
/* 160 */     this.correnteSaida220F1MB = 0.020408163F;
/* 161 */     this.correnteSaida220F2MB = 0.1F;
/* 162 */     this.correnteSaida220F1MR = 0.020408163F;
/* 163 */     this.correnteSaida220F2MR = 0.1F;
/* 164 */     this.correnteSaida110F1MB = 0.04597701F;
/* 165 */     this.correnteSaida110F2MB = 0.15F;
/* 166 */     this.correnteSaida110F1MR = 0.04597701F;
/* 167 */     this.correnteSaida110F2MR = 0.15F;
/* 168 */     this.potenciaAparenteOffset = 28.0F;
/* 169 */     this.potenciaAparente220F1MR = 0.064935066F;
/* 170 */     this.potenciaAparente220F2MR = 22.0F;
/* 171 */     this.potenciaAparente220F1MB = 0.064935066F;
/* 172 */     this.potenciaAparente220F2MB = 22.0F;
/* 173 */     this.potenciaAparente110F1MR = 0.074074075F;
/* 174 */     this.potenciaAparente110F2MR = 16.0F;
/* 175 */     this.potenciaAparente110F1MB = 0.07751938F;
/* 176 */     this.potenciaAparente110F2MB = 17.0F;
/* 177 */     this.potenciaReal220F1MR = 0.06896552F;
/* 178 */     this.potenciaReal220F2MR = 20.0F;
/* 179 */     this.potenciaReal220F1MB = 0.071428575F;
/* 180 */     this.potenciaReal220F2MB = 25.0F;
/* 181 */     this.potenciaReal110F1MR = 0.07770008F;
/* 182 */     this.potenciaReal110F2MR = 15.6F;
/* 183 */     this.potenciaReal110F1MB = 0.08196721F;
/* 184 */     this.potenciaReal110F2MB = 13.0F;
/* 185 */     this.correnteEntradaF1MR = 22.2F;
/* 186 */     this.correnteEntradaF2MR = 800.0F;
/* 187 */     this.bateriaMaximoMR = 216.0F;
/* 188 */     this.bateriaMinimoMR = 156.0F;
/* 189 */     this.bateriaMaximoMB = 216.0F;
/* 190 */     this.bateriaMinimoMB = 136.0F;
/* 191 */     this.comparadorAutonomiaBateria = 5;
/* 192 */     this.comparadorContadorAutonomiaBaixa = 5;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean ligaEntrada()
/*     */   {
/* 201 */     return this.protocoloUPS.ligaEntrada();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean desligaEntrada()
/*     */   {
/* 209 */     return this.protocoloUPS.desligaEntrada();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean ligaSaida()
/*     */   {
/* 216 */     return this.protocoloUPS.ligaSaida();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean desligaSaida()
/*     */   {
/* 223 */     return this.protocoloUPS.desligaSaida();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean ativaBypass()
/*     */   {
/* 230 */     return this.protocoloUPS.ativaBypass();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean desativaBypass()
/*     */   {
/* 237 */     return this.protocoloUPS.desativaBypass();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean iniciarAutoteste()
/*     */   {
/* 244 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean finalizarAutoteste()
/*     */   {
/* 251 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean shutdown()
/*     */   {
/* 258 */     return this.protocoloUPS.shutdown();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean shutdownReligamento()
/*     */   {
/* 265 */     return this.protocoloUPS.shutdownReligamento();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void downloadEventos()
/*     */   {
/* 272 */     this.eventos = this.protocoloUPS.downloadEventos();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean configuraRelogio(int segundos, int minutos, int horas)
/*     */   {
/* 281 */     return this.protocoloUPS.configuraRelogio(segundos, minutos, horas);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean configuraCalendario(int diaSemana, int diaMes, int mes, int ano)
/*     */   {
/* 290 */     return configuraCalendario(diaSemana, diaMes, mes, ano);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean configuraCalendarioRelogio(int diaSemana, int diaMes, int mes, int ano, int segundo, int minuto, int hora)
/*     */   {
/* 299 */     return this.protocoloUPS.configuraCalendarioRelogio(diaSemana, diaMes, mes, ano, this.segundos, this.minutos, hora);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean programaSemana(boolean[] dias, int horaLigar, int minutoLigar, int horaDesligar, int minutoDesligar)
/*     */   {
/* 307 */     setHoraLigar(horaLigar);
/* 308 */     setMinutoLigar(minutoLigar);
/* 309 */     setHoraDesligar(horaDesligar);
/* 310 */     setMinutoDesligar(minutoDesligar);
/* 311 */     setDiasSemanaProgramados(dias);
/*     */     
/* 313 */     return this.protocoloUPS.programaSemana(dias, horaLigar, minutoLigar, horaDesligar, minutoDesligar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean programa(boolean[] dias, int horaLigar, int minutoLigar, int horaDesligar, int minutoDesligar, int diaSemana, int diaMes, int mes, int ano, int segundo, int minuto, int hora)
/*     */   {
/* 323 */     setHoraLigar(horaLigar);
/* 324 */     setHoraDesligar(horaDesligar);
/* 325 */     setMinutoLigar(minutoLigar);
/* 326 */     setMinutoDesligar(minutoDesligar);
/* 327 */     setDiasSemanaProgramados(dias);
/*     */     
/* 329 */     return this.protocoloUPS.configuraCalendarioRelogioSemana(diaSemana, diaMes, 
/* 330 */       mes, ano, segundo, minuto, hora, dias, horaLigar, minutoLigar, horaDesligar, minutoDesligar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setAno(int ano)
/*     */   {
/* 337 */     this.ano = ano;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAutonomiaBateria(int tensaoBateriaBruta)
/*     */   {
/* 345 */     if (this.potenciaReal <= 20.0F) {
/* 346 */       this.autonomiaBateria = 170;
/* 347 */     } else if ((this.potenciaReal > 20.0F) && (this.potenciaReal <= 150.0F))
/*     */     {
/*     */       try {
/* 350 */         this.autonomiaBateria = this.calcAut0100[(tensaoBateriaBruta - 139)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {}
/* 353 */       if (tensaoBateriaBruta > 176) {
/* 354 */         this.autonomiaBateria = 75;
/*     */       }
/* 356 */       if (tensaoBateriaBruta < 139) {
/* 357 */         this.autonomiaBateria = 0;
/*     */       }
/* 359 */     } else if ((this.potenciaReal > 150.0F) && (this.potenciaReal <= 250.0F))
/*     */     {
/*     */       try {
/* 362 */         this.autonomiaBateria = this.calcAut0200[(tensaoBateriaBruta - 139)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException1) {}
/* 365 */       if (tensaoBateriaBruta > 174) {
/* 366 */         this.autonomiaBateria = 34;
/*     */       }
/* 368 */       if (tensaoBateriaBruta < 139) {
/* 369 */         this.autonomiaBateria = 0;
/*     */       }
/* 371 */     } else if ((this.potenciaReal > 250.0F) && (this.potenciaReal <= 350.0F))
/*     */     {
/*     */       try {
/* 374 */         this.autonomiaBateria = this.calcAut0300[(tensaoBateriaBruta - 139)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException2) {}
/* 377 */       if (tensaoBateriaBruta > 172) {
/* 378 */         this.autonomiaBateria = 23;
/*     */       }
/* 380 */       if (tensaoBateriaBruta < 142) {
/* 381 */         this.autonomiaBateria = 0;
/*     */       }
/* 383 */     } else if ((this.potenciaReal > 350.0F) && (this.potenciaReal <= 450.0F))
/*     */     {
/*     */       try {
/* 386 */         this.autonomiaBateria = this.calcAut0400[(tensaoBateriaBruta - 139)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException3) {}
/* 389 */       if (tensaoBateriaBruta > 172) {
/* 390 */         this.autonomiaBateria = 20;
/*     */       }
/* 392 */       if (tensaoBateriaBruta < 141) {
/* 393 */         this.autonomiaBateria = 0;
/*     */       }
/* 395 */     } else if ((this.potenciaReal > 450.0F) && (this.potenciaReal <= 550.0F))
/*     */     {
/*     */       try {
/* 398 */         this.autonomiaBateria = this.calcAut0500[(tensaoBateriaBruta - 139)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException4) {}
/* 401 */       if (tensaoBateriaBruta > 170) {
/* 402 */         this.autonomiaBateria = 14;
/*     */       }
/* 404 */       if (tensaoBateriaBruta < 141) {
/* 405 */         this.autonomiaBateria = 0;
/*     */       }
/* 407 */     } else if ((this.potenciaReal > 550.0F) && (this.potenciaReal <= 650.0F))
/*     */     {
/*     */       try {
/* 410 */         this.autonomiaBateria = this.calcAut0600[(tensaoBateriaBruta - 139)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException5) {}
/* 413 */       if (tensaoBateriaBruta > 170) {
/* 414 */         this.autonomiaBateria = 12;
/*     */       }
/* 416 */       if (tensaoBateriaBruta < 141) {
/* 417 */         this.autonomiaBateria = 0;
/*     */       }
/* 419 */     } else if (this.potenciaReal > 650.0F)
/*     */     {
/*     */       try {
/* 422 */         this.autonomiaBateria = this.calcAut0700[(tensaoBateriaBruta - 139)];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException6) {}
/* 425 */       if (tensaoBateriaBruta > 170) {
/* 426 */         this.autonomiaBateria = 10;
/*     */       }
/* 428 */       if (tensaoBateriaBruta < 141) {
/* 429 */         this.autonomiaBateria = 0;
/*     */       }
/*     */     }
/*     */     
/* 433 */     if (this.expansorBateria > 0) {
/* 434 */       this.autonomiaBateria = (this.autonomiaBateria * (this.expansorBateria + 14) / 14);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBateriaBaixa(boolean bateriaBaixa)
/*     */   {
/* 443 */     this.bateriaBaixa = bateriaBaixa;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBateriaCarregada(boolean bateriaCarregada)
/*     */   {
/* 451 */     this.bateriaCarregada = bateriaCarregada;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBateriaCritica(boolean bateriaCritica)
/*     */   {
/* 459 */     this.bateriaCritica = bateriaCritica;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUsandoSomenteBateria(boolean usandoSomenteBateria)
/*     */   {
/* 467 */     this.usandoSomenteBateria = usandoSomenteBateria;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBateriaDescarregada(boolean bateriaDescarregada)
/*     */   {
/* 475 */     this.bateriaDescarregada = bateriaDescarregada;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBypassAtivado(boolean bypassAtivado)
/*     */   {
/* 483 */     this.bypassAtivado = bypassAtivado;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCargaElevada(boolean cargaElevada)
/*     */   {
/* 491 */     this.cargaElevada = cargaElevada;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCarregandoBateria(boolean carregandoBateria)
/*     */   {
/* 499 */     this.carregandoBateria = carregandoBateria;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setCorrenteEntrada(int correnteEntrada)
/*     */   {
/* 505 */     this.correnteEntrada = (correnteEntrada / 2);
/*     */   }
/*     */   
/*     */   public void setCorrenteSaida(int correnteSaida)
/*     */   {
/* 510 */     if (this.tensaoSaida > 160.0F) {
/* 511 */       this.correnteSaida = (correnteSaida / 2);
/*     */     } else {
/* 513 */       this.correnteSaida = correnteSaida;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDiaMes(int diaMes) {
/* 518 */     this.diaMes = diaMes;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setDiaSemana(int diaSemana)
/*     */   {
/* 524 */     this.diaSemana = diaSemana;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setDiasSemanaProgramados(boolean[] diasSemanaProgramados)
/*     */   {
/* 530 */     this.diasSemanaProgramados = diasSemanaProgramados;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setEntradaLigada(boolean entradaLigada)
/*     */   {
/* 536 */     this.entradaLigada = entradaLigada;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setExpansorBateria(int expansorBateria)
/*     */   {
/* 542 */     this.expansorBateria = expansorBateria;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setFatorPotenciaCarga(int fatorPotenciaCarga)
/*     */   {
/* 548 */     this.fatorPotenciaCarga = (fatorPotenciaCarga / 100.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setFrequenciaEntrada(int frequenciaEntrada)
/*     */   {
/* 554 */     this.frequenciaEntrada = frequenciaEntrada;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setFrequenciaSaida(int frequenciaSaida)
/*     */   {
/* 560 */     this.frequenciaSaida = frequenciaSaida;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setHora(int hora)
/*     */   {
/* 566 */     this.hora = hora;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setHoraDesligar(int horaDesligar)
/*     */   {
/* 572 */     this.horaDesligar = horaDesligar;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setHoraLigar(int horaLigar)
/*     */   {
/* 578 */     this.horaLigar = horaLigar;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLimiteInferiorTensaoEntrada(int limiteInferiorTensaoEntrada)
/*     */   {
/* 589 */     if (this.tensaoEntrada > 180.0F) {
/* 590 */       this.limiteInferiorTensaoEntrada = 150.0F;
/*     */     } else {
/* 592 */       this.limiteInferiorTensaoEntrada = 75.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLimiteInferiorTensaoSaida(int limiteInferiorTensaoSaida)
/*     */   {
/* 603 */     if (this.tensaoSaida > 180.0F) {
/* 604 */       this.limiteInferiorTensaoSaida = 190.0F;
/*     */     } else {
/* 606 */       this.limiteInferiorTensaoSaida = 100.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLimiteSuperiorTensaoEntrada(int limiteSuperiorTensaoEntrada)
/*     */   {
/* 617 */     if (this.tensaoEntrada > 180.0F) {
/* 618 */       this.limiteSuperiorTensaoEntrada = 300.0F;
/*     */     } else {
/* 620 */       this.limiteSuperiorTensaoEntrada = 150.0F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLimiteSuperiorTensaoSaida(int limiteSuperiorTensaoSaida)
/*     */   {
/* 631 */     if (this.tensaoSaida > 180.0F) {
/* 632 */       this.limiteSuperiorTensaoSaida = 250.0F;
/*     */     } else {
/* 634 */       this.limiteSuperiorTensaoSaida = 140.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setMes(int mes)
/*     */   {
/* 640 */     this.mes = mes;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setMinutoDesligar(int minutoDesligar)
/*     */   {
/* 646 */     this.minutoDesligar = minutoDesligar;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setMinutoLigar(int minutoLigar)
/*     */   {
/* 652 */     this.minutoLigar = minutoLigar;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setMinutos(int minutos)
/*     */   {
/* 658 */     this.minutos = minutos;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setModoBateria(boolean modoBateria)
/*     */   {
/* 664 */     this.modoBateria = modoBateria;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setModoBypass(boolean modoBypass)
/*     */   {
/* 670 */     this.modoBypass = modoBypass;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setModoRede(boolean modoRede)
/*     */   {
/* 676 */     this.modoRede = modoRede;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setPercentualBateria(int percentualBateria)
/*     */   {
/* 682 */     if (!this.modoRede) {
/* 683 */       this.percentualBateria = 
/* 684 */         ((int)((this.tensaoBateria - this.bateriaMinimoMB) * (100.0F / (this.bateriaMaximoMB - this.bateriaMinimoMB))));
/*     */     } else {
/* 686 */       this.percentualBateria = 
/* 687 */         ((int)((this.tensaoBateria - this.bateriaMinimoMR) * (100.0F / (this.bateriaMaximoMR - this.bateriaMinimoMR))));
/*     */     }
/* 689 */     if (this.percentualBateria < 0) {
/* 690 */       this.percentualBateria = 0;
/*     */     }
/* 692 */     if (this.percentualBateria > 100)
/*     */     {
/* 694 */       if (this.percentualBateria > 200) {
/* 695 */         this.percentualBateria = 0;
/*     */       } else
/* 697 */         this.percentualBateria = 100;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setPotenciaAparenteEntrada(float potenciaAparenteEntrada) {
/* 702 */     this.potenciaAparenteEntrada = potenciaAparenteEntrada;
/*     */   }
/*     */   
/*     */   public void setPotenciaAparenteSaida(float potenciaAparenteSaida)
/*     */   {
/* 707 */     this.potenciaAparenteSaida = potenciaAparenteSaida;
/*     */   }
/*     */   
/*     */   public void setPotenciaRealEntrada(float potenciaRealEntrada)
/*     */   {
/* 712 */     this.potenciaRealEntrada = potenciaRealEntrada;
/*     */   }
/*     */   
/*     */   public void setPotenciaRealSaida(float potenciaRealSaida)
/*     */   {
/* 717 */     this.potenciaRealSaida = potenciaRealSaida;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSaidaLigada(boolean saidaLigada)
/*     */   {
/* 724 */     this.saidaLigada = saidaLigada;
/*     */   }
/*     */   
/*     */   public void setSegundos(int segundos)
/*     */   {
/* 729 */     this.segundos = segundos;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSobrecarga(boolean sobrecarga)
/*     */   {
/* 735 */     this.sobrecarga = sobrecarga;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSuperAquecimento(boolean superAquecimento)
/*     */   {
/* 741 */     this.superAquecimento = superAquecimento;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTemperaturaElevada(boolean temperaturaElevada)
/*     */   {
/* 747 */     this.temperaturaElevada = temperaturaElevada;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTemperaturaUPS(int temperaturaUPS)
/*     */   {
/* 753 */     this.temperaturaUPS = temperaturaUPS;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTensaoBateria(int tensaoBateria)
/*     */   {
/* 759 */     this.tensaoBateria = tensaoBateria;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTensaoBoost(int tensaoBoost)
/*     */   {
/* 765 */     this.tensaoBoost = tensaoBoost;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTensaoEntrada(int tensaoEntrada)
/*     */   {
/* 771 */     this.tensaoEntrada = tensaoEntrada;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoEntradaNominal(int tensaoEntrada)
/*     */   {
/* 783 */     if (tensaoEntrada > 180) {
/* 784 */       this.tensaoEntradaNominal = 220.0F;
/*     */     }
/*     */     else {
/* 787 */       this.tensaoEntradaNominal = 110.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setTensaoEntrada220(boolean tensaoEntrada220)
/*     */   {
/* 793 */     this.tensaoEntrada220 = tensaoEntrada220;
/*     */   }
/*     */   
/*     */   public void setTensaoSaida(int tensaoSaida)
/*     */   {
/* 798 */     this.tensaoSaida = tensaoSaida;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTensaoSaidaNominal(int tensaoSaidaNominal)
/*     */   {
/* 809 */     if (this.tensaoSaida220) {
/* 810 */       this.tensaoSaidaNominal = 220.0F;
/*     */     }
/*     */     else {
/* 813 */       this.tensaoSaidaNominal = 110.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setTensaoSaida220(boolean tensaoSaida220)
/*     */   {
/* 819 */     this.tensaoSaida220 = tensaoSaida220;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setFatorPotenciaEquipamento(int fatorPotenciaEquipamento)
/*     */   {
/* 825 */     this.fatorPotenciaEquipamento = fatorPotenciaEquipamento;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setTemperaturaCritica(int temperaturaCritica)
/*     */   {
/* 831 */     this.temperaturaCritica = temperaturaCritica;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setPotenciaNominalVA(int potenciaNominalVA)
/*     */   {
/* 837 */     this.potenciaNominalVA = potenciaNominalVA;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setPotenciaNominalW(int potenciaNominalW)
/*     */   {
/* 843 */     this.potenciaNominalW = potenciaNominalW;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBateriaTensaoNominal(int bateriaTensaoNominal)
/*     */   {
/* 849 */     this.bateriaTensaoNominal = bateriaTensaoNominal;
/*     */   }
/*     */   
/*     */ 
/*     */   public void notifica()
/*     */   {
/* 855 */     setModoRede(this.protocoloUPS.getModoRede());
/* 856 */     setTensaoEntrada220(this.protocoloUPS.isTensaoEntrada220());
/* 857 */     setTensaoEntradaNominal(this.protocoloUPS.getTensaoEntrada());
/* 858 */     setTensaoEntrada(this.protocoloUPS.getTensaoEntrada());
/* 859 */     setTensaoBateria(this.protocoloUPS.getTensaoBateria());
/* 860 */     setTensaoSaida220(this.protocoloUPS.isTensaoSaida220());
/* 861 */     setPotenciaAparente(this.protocoloUPS.getPotenciaAparente());
/* 862 */     setCorrenteSaida(this.protocoloUPS.getCorrenteSaida());
/* 863 */     setModoBateria(this.protocoloUPS.getModoBateria());
/* 864 */     setTensaoSaida(this.protocoloUPS.getTensaoSaida());
/* 865 */     setCorrenteEntrada(this.protocoloUPS.getCorrenteEntrada());
/* 866 */     setFrequenciaEntrada(this.protocoloUPS.getFrequenciaEntrada());
/* 867 */     setLimiteInferiorTensaoEntrada(this.protocoloUPS.getLimiteInferiorTensaoEntrada());
/* 868 */     setLimiteSuperiorTensaoEntrada(this.protocoloUPS.getLimiteSuperiorTensaoEntrada());
/* 869 */     setTensaoSaidaNominal(this.protocoloUPS.getTensaoSaidaNominal());
/* 870 */     setFrequenciaSaida(this.protocoloUPS.getFrequenciaSaida());
/* 871 */     setLimiteInferiorTensaoSaida(this.protocoloUPS.getLimiteInferiorTensaoSaida());
/* 872 */     setLimiteSuperiorTensaoSaida(this.protocoloUPS.getLimiteSuperiorTensaoSaida());
/* 873 */     setPotenciaReal(this.protocoloUPS.getPotenciaReal());
/* 874 */     setFatorPotenciaCarga(this.protocoloUPS.getFatorPotenciaCarga());
/* 875 */     setTensaoBoost(this.protocoloUPS.getTensaoBoost());
/* 876 */     setTemperaturaUPS(this.protocoloUPS.getTemperaturaUPS());
/* 877 */     setPercentualBateria(this.protocoloUPS.getPercentualBateria());
/* 878 */     setAutonomiaBateria(this.protocoloUPS.getTensaoBateria());
/* 879 */     setCarregandoBateria(this.protocoloUPS.isCarregandoBateria());
/* 880 */     setUsandoSomenteBateria(this.protocoloUPS.isUsandoSomenteBateria());
/* 881 */     setModoBypass(this.protocoloUPS.isModoBypass());
/* 882 */     setSuperAquecimento(this.protocoloUPS.isSuperAquecimento());
/* 883 */     setTemperaturaElevada(this.protocoloUPS.isTemperaturaElevada());
/* 884 */     setBateriaBaixa(this.protocoloUPS.isBateriaBaixa());
/* 885 */     setBateriaCritica(this.protocoloUPS.isBateriaCritica());
/* 886 */     setCargaElevada(this.protocoloUPS.isCargaElevada());
/* 887 */     setBateriaDescarregada(this.protocoloUPS.isBateriaDescarregada());
/* 888 */     setBateriaCarregada(this.protocoloUPS.isBateriaCarregada());
/* 889 */     setSobrecarga(this.protocoloUPS.isSobrecarga());
/* 890 */     setSegundos(this.protocoloUPS.getSegundos());
/* 891 */     setMinutos(this.protocoloUPS.getMinutos());
/* 892 */     setHora(this.protocoloUPS.getHora());
/* 893 */     setDiaSemana(this.protocoloUPS.getDiaSemana());
/* 894 */     setDiaMes(this.protocoloUPS.getDiaMes());
/* 895 */     setMes(this.protocoloUPS.getMes());
/* 896 */     setAno(this.protocoloUPS.getAno());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 902 */     setSaidaLigada(this.protocoloUPS.isSaidaLigada());
/* 903 */     setEntradaLigada(this.protocoloUPS.isEntradaLigada());
/* 904 */     setBypassAtivado(this.protocoloUPS.isBypassAtivado());
/* 905 */     downloadEventos();
/*     */     
/* 907 */     this.ouvinteUPS.notificaControle();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getPotenciaAparenteEntrada()
/*     */   {
/* 915 */     return this.potenciaAparenteEntrada;
/*     */   }
/*     */   
/*     */ 
/*     */   public float getPotenciaAparenteSaida()
/*     */   {
/* 921 */     return this.potenciaAparenteSaida;
/*     */   }
/*     */   
/*     */ 
/*     */   public float getPotenciaRealEntrada()
/*     */   {
/* 927 */     return this.potenciaRealEntrada;
/*     */   }
/*     */   
/*     */ 
/*     */   public float getPotenciaRealSaida()
/*     */   {
/* 933 */     return this.potenciaRealSaida;
/*     */   }
/*     */   
/*     */   public float getPotenciaAparente() {
/* 937 */     return this.potenciaAparente;
/*     */   }
/*     */   
/*     */ 
/*     */   public float getPotenciaReal()
/*     */   {
/* 943 */     return this.potenciaReal;
/*     */   }
/*     */   
/*     */   public void setPotenciaAparente(int potenciaAparente)
/*     */   {
/* 948 */     this.potenciaAparente = potenciaAparente;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPotenciaReal(int potenciaReal)
/*     */   {
/* 955 */     this.potenciaReal = potenciaReal;
/*     */   }
/*     */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\dispositivos\Rhino.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */