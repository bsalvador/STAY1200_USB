/*      */ package br.com.schneider.sgm.protocolo;
/*      */ 
/*      */ import br.com.schneider.sgm.comunicacao.Communication;
/*      */ import br.com.schneider.sgm.eventos.Evento;
/*      */ import br.com.schneider.sgm.eventos.ProtocoloListener;
/*      */ import java.io.PrintStream;
/*      */ import java.util.Calendar;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.Timer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ProtocoloSolis
/*      */   extends ProtocoloUPS
/*      */ {
/*   17 */   private final int TEMPO_TIMEOUT = 1000;
/*   18 */   private final int ANO_BASE = 1998;
/*   19 */   protected final int RECONHECIMENTO_COMANDO = 172;
/*   20 */   protected final int COMANDO_INEXISTENTE = 238;
/*   21 */   protected final int COMANDO_LIGAR_ENTRADA = 194;
/*   22 */   protected final int COMANDO_DESLIGAR_ENTRADA = 195;
/*      */   
/*      */   protected boolean esperaDumpping;
/*      */   
/*      */   protected int[] pacoteDados;
/*      */   protected int[] pacoteDumpping;
/*      */   protected int[] vetorBytes;
/*   29 */   protected int numeroAlarmesMicrosol = 0;
/*      */   
/*      */ 
/*      */   protected Evento[] alarmesMicrosol;
/*      */   
/*      */   protected Evento evento;
/*      */   
/*      */   protected boolean modoContinuo;
/*      */   
/*      */   protected Timer timeout;
/*      */   
/*      */ 
/*      */   public ProtocoloSolis()
/*      */   {
/*   43 */     this.timeout = new Timer();
/*   44 */     this.pacoteDados = new int[25];
/*   45 */     this.pacoteDumpping = new int['È'];
/*   46 */     this.alarmesMicrosol = new Evento[100];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void notifica()
/*      */   {
/*   56 */     recebeDados();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void recebeDados()
/*      */   {
/*   64 */     System.out.println("Recebi!!!");
/*      */     
/*   66 */     this.vetorBytes = this.comunicador.getBytes();
/*   67 */     int i = 0;int checksum = 0;
/*   68 */     while (this.vetorBytes[(i++)] != -1) {}
/*   69 */     int numeroBytes = i - 2;
/*      */     
/*   71 */     switch (numeroBytes)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 1: 
/*      */     case 2: 
/*      */     case 3: 
/*   81 */       if ((this.esperaACK) && (this.vetorBytes[0] == 172)) {
/*   82 */         this.esperaACK = false;
/*      */       }
/*      */       
/*   85 */       if (this.esperaACK) { this.vetorBytes[0];
/*      */       }
/*      */       
/*   88 */       break;
/*      */     
/*      */ 
/*      */     case 25: 
/*   92 */       if (this.vetorBytes[20] != this.pacoteDados[20]) {
/*   93 */         pedidoDumpping();
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*   99 */       if (this.vetorBytes[24] == 254) {
/*  100 */         for (i = 0; i <= 24; i++) {
/*  101 */           this.pacoteDados[i] = this.vetorBytes[i];
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  106 */         for (i = 0; i <= 22; i++)
/*  107 */           checksum = this.pacoteDados[i] + checksum;
/*  108 */         checksum %= 256;
/*      */         
/*      */ 
/*  111 */         if (this.pacoteDados[23] == checksum)
/*      */         {
/*      */ 
/*  114 */           trataPacoteDados();
/*  115 */           checksum = 0;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  126 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     default: 
/*  135 */       if ((this.vetorBytes[0] == 172) && (numeroBytes >= 5)) {
/*  136 */         boolean t = false;
/*  137 */         int j = 0;
/*  138 */         int z = 0;
/*  139 */         int cnt = 0;
/*  140 */         int pos = 1;
/*      */         
/*  142 */         while (this.vetorBytes[j] == 172)
/*      */         {
/*  144 */           j++;
/*  145 */           z++;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  150 */         this.pacoteDumpping[0] = 172;
/*  151 */         checksum = 0;
/*  152 */         while (z < numeroBytes)
/*      */         {
/*  154 */           this.pacoteDumpping[pos] = this.vetorBytes[z];
/*      */           
/*  156 */           checksum += this.pacoteDumpping[pos];
/*  157 */           cnt++;
/*  158 */           if (cnt == 3)
/*      */           {
/*      */ 
/*  161 */             cnt = 0;
/*      */             
/*  163 */             if ((this.pacoteDumpping[pos] & 0x80) == 128)
/*      */             {
/*      */ 
/*  166 */               int checksum2 = checksum % 256;
/*      */               
/*      */ 
/*  169 */               if (checksum2 != this.vetorBytes[(z + 1)])
/*      */                 break;
/*  171 */               this.pacoteDumpping[(pos + 1)] = this.vetorBytes[(z + 1)];
/*  172 */               int numeroEventos = pos / 3;
/*      */               
/*  174 */               trataPacoteDumpping(numeroEventos);
/*  175 */               this.esperaDumpping = false;
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  182 */           z++;
/*  183 */           pos++;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */       break;
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void trataPacoteDados()
/*      */   {
/*  199 */     setCabecalhoPacote(this.pacoteDados[0]);
/*  200 */     setTensaoSaida(this.pacoteDados[1]);
/*      */     
/*  202 */     setTensaoBateria(this.pacoteDados[3]);
/*  203 */     setTemperaturaUPS(this.pacoteDados[4]);
/*  204 */     setCorrenteSaida(this.pacoteDados[5]);
/*  205 */     setTensaoEntrada(this.pacoteDados[6]);
/*  206 */     setPotenciaReal(this.pacoteDados[7], this.pacoteDados[8]);
/*  207 */     setSegundos(this.pacoteDados[9]);
/*  208 */     setMinutos(this.pacoteDados[10]);
/*  209 */     setHora(this.pacoteDados[11]);
/*      */     
/*  211 */     setHoraLigar(this.pacoteDados[13]);
/*  212 */     setMinutoLigar(this.pacoteDados[14]);
/*  213 */     setHoraDesligar(this.pacoteDados[15]);
/*  214 */     setMinutoDesligar(this.pacoteDados[16]);
/*  215 */     setDiaSemana(this.pacoteDados[18]);
/*  216 */     setDiasSemanaProgramados(this.pacoteDados[17]);
/*  217 */     setDiaMes(this.pacoteDados[18]);
/*  218 */     setAno(this.pacoteDados[19]);
/*  219 */     setMes(this.pacoteDados[19]);
/*  220 */     setTensaoSaida220(this.pacoteDados[20]);
/*  221 */     setCarregandoBateria(this.pacoteDados[20]);
/*  222 */     setBateriaCritica(this.pacoteDados[20]);
/*  223 */     setModoBateria(this.pacoteDados[20]);
/*  224 */     setSuperAquecimento(this.pacoteDados[20]);
/*  225 */     setModoRede(this.pacoteDados[20]);
/*  226 */     setTensaoEntrada220(this.pacoteDados[20]);
/*  227 */     setSobrecarga(this.pacoteDados[20]);
/*  228 */     setModoBypass(this.pacoteDados[20]);
/*  229 */     setFrequenciaEntrada(this.pacoteDados[21], this.pacoteDados[22]);
/*      */     
/*  231 */     this.ouvinteProtocolo.notifica();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void trataPacoteDumpping(int numeroEventos)
/*      */   {
/*  243 */     Calendar calendar = new GregorianCalendar();
/*      */     
/*      */ 
/*  246 */     setModoContinuo();
/*  247 */     this.esperaACK = false;
/*      */     
/*      */ 
/*      */ 
/*  251 */     for (int i = 0; i < numeroEventos; i++)
/*      */     {
/*  253 */       int hora = (this.pacoteDumpping[(i * 3 + 1)] & 0xF8) >> 3;
/*  254 */       int minuto = (this.pacoteDumpping[(i * 3 + 1)] & 0x7) << 3 | (this.pacoteDumpping[(i * 3 + 2)] & 0xE0) >> 5;
/*  255 */       int segundo = calendar.get(13);
/*  256 */       int tipo = this.pacoteDumpping[(i * 3 + 2)] & 0x1F;
/*  257 */       int offSet = this.pacoteDumpping[(i * 3 + 3)] & 0x7F;
/*      */       
/*  259 */       int mesAtual = calendar.get(2) + 1;
/*  260 */       int anoAtual = calendar.get(1);
/*      */       
/*  262 */       if ((hora <= 23) && (minuto <= 59) && (getDiaMes() + offSet <= 31)) {
/*  263 */         this.evento = new Evento(tipo, hora, minuto, segundo, getDiaMes() + offSet, mesAtual, anoAtual);
/*  264 */         addEvento(this.evento);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  271 */     if ((this.pacoteDumpping[(i * 3 + 3)] & 0x80) == 1) {
/*  272 */       this.esperaDumpping = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addEvento(Evento evt)
/*      */   {
/*  282 */     this.alarmesMicrosol[this.numeroAlarmesMicrosol] = evt;
/*  283 */     this.numeroAlarmesMicrosol += 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean ligaSaida()
/*      */   {
/*  290 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean desligaSaida()
/*      */   {
/*  296 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean ativaBypass()
/*      */   {
/*  302 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean desativaBypass()
/*      */   {
/*  308 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean configuraRelogio(int segundos, int minutos, int horas)
/*      */   {
/*  315 */     int[] bytes = new int[12];
/*  316 */     int checksum = 0;int temp = 0;
/*      */     
/*  318 */     bytes[0] = 207;
/*      */     
/*  320 */     bytes[1] = horas;
/*  321 */     bytes[2] = minutos;
/*  322 */     bytes[3] = segundos;
/*      */     
/*  324 */     bytes[4] = getHoraLigar();
/*  325 */     bytes[5] = getMinutoLigar();
/*      */     
/*  327 */     bytes[6] = getHoraDesligar();
/*  328 */     bytes[7] = getMinutoDesligar();
/*      */     
/*  330 */     bytes[8] = (getDiaSemana() << 5);
/*  331 */     bytes[8] |= getDiaMes();
/*  332 */     bytes[9] = (getMes() << 4);
/*  333 */     bytes[9] |= getAno() - 1998;
/*  334 */     int semana = 0;
/*  335 */     temp = 0;
/*  336 */     boolean[] dias = getDiasSemanaProgramados();
/*      */     
/*      */ 
/*  339 */     for (int i = 1; i < 7; i++) {
/*  340 */       if (dias[i] != 0) {
/*  341 */         temp = 1;
/*      */       } else
/*  343 */         temp = 0;
/*  344 */       semana |= temp << 7 - i;
/*      */     }
/*  346 */     if (dias[0] != 0) {
/*  347 */       temp = 1;
/*      */     } else
/*  349 */       temp = 0;
/*  350 */     semana |= temp;
/*      */     
/*  352 */     bytes[10] = semana;
/*  353 */     if (this.modoContinuo) {
/*  354 */       temp = 1;
/*      */     } else {
/*  356 */       temp = 0;
/*      */     }
/*  358 */     bytes[10] |= temp & 0x80;
/*      */     
/*      */ 
/*      */ 
/*  362 */     for (int i = 0; i <= 10; i++) {
/*  363 */       checksum += bytes[i];
/*  364 */       bytes[i] = bytes[i];
/*      */     }
/*  366 */     bytes[11] = (checksum % 256);
/*  367 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean configuraCalendario(int diaSemana, int diaMes, int mes, int ano)
/*      */   {
/*  373 */     int[] bytes = new int[12];
/*  374 */     int checksum = 0;int temp = 0;
/*      */     
/*  376 */     bytes[0] = 207;
/*      */     
/*  378 */     bytes[1] = getHora();
/*  379 */     bytes[2] = getMinutos();
/*  380 */     bytes[3] = getSegundos();
/*      */     
/*  382 */     bytes[4] = getHoraLigar();
/*  383 */     bytes[5] = getMinutoLigar();
/*      */     
/*  385 */     bytes[6] = getHoraDesligar();
/*  386 */     bytes[7] = getMinutoDesligar();
/*      */     
/*  388 */     bytes[8] = (diaSemana << 5);
/*  389 */     bytes[8] |= diaMes;
/*  390 */     bytes[9] = (mes << 4);
/*  391 */     bytes[9] |= ano - 1998;
/*  392 */     int semana = 0;
/*  393 */     temp = 0;
/*  394 */     boolean[] dias = getDiasSemanaProgramados();
/*      */     
/*      */ 
/*  397 */     for (int i = 1; i < 7; i++) {
/*  398 */       if (dias[i] != 0) {
/*  399 */         temp = 1;
/*      */       } else
/*  401 */         temp = 0;
/*  402 */       semana |= temp << 7 - i;
/*      */     }
/*  404 */     if (dias[0] != 0) {
/*  405 */       temp = 1;
/*      */     } else
/*  407 */       temp = 0;
/*  408 */     semana |= temp;
/*      */     
/*  410 */     bytes[10] = semana;
/*  411 */     if (this.modoContinuo) {
/*  412 */       temp = 1;
/*      */     } else {
/*  414 */       temp = 0;
/*      */     }
/*  416 */     bytes[10] |= temp & 0x80;
/*      */     
/*      */ 
/*      */ 
/*  420 */     for (int i = 0; i <= 10; i++) {
/*  421 */       checksum += bytes[i];
/*  422 */       bytes[i] = bytes[i];
/*      */     }
/*  424 */     bytes[11] = (checksum % 256);
/*      */     
/*  426 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean programaSemana(boolean[] dias, int horaLigar, int minutoLigar, int horaDesligar, int minutoDesligar)
/*      */   {
/*  434 */     int[] bytes = new int[12];
/*  435 */     int checksum = 0;int temp = 0;
/*      */     
/*  437 */     bytes[0] = 207;
/*      */     
/*  439 */     bytes[1] = getHora();
/*  440 */     bytes[2] = getMinutos();
/*  441 */     bytes[3] = getSegundos();
/*      */     
/*  443 */     bytes[4] = horaLigar;
/*  444 */     bytes[5] = minutoLigar;
/*      */     
/*  446 */     bytes[6] = horaDesligar;
/*  447 */     bytes[7] = minutoDesligar;
/*      */     
/*  449 */     bytes[8] = (getDiaSemana() << 5);
/*  450 */     bytes[8] |= getDiaMes();
/*  451 */     temp = getMes();
/*  452 */     bytes[9] = (temp << 4);
/*  453 */     bytes[9] |= getAno() - 1998;
/*  454 */     int semana = 0;
/*  455 */     temp = 0;
/*      */     
/*      */ 
/*  458 */     for (int i = 1; i < 7; i++)
/*      */     {
/*  460 */       if (dias[i] != 0) {
/*  461 */         temp = 1;
/*      */       } else
/*  463 */         temp = 0;
/*  464 */       semana |= temp << 7 - i;
/*      */     }
/*  466 */     if (dias[0] != 0) {
/*  467 */       temp = 1;
/*      */     } else
/*  469 */       temp = 0;
/*  470 */     semana |= temp;
/*      */     
/*  472 */     bytes[10] = semana;
/*  473 */     if (this.modoContinuo) {
/*  474 */       temp = 1;
/*      */     } else {
/*  476 */       temp = 0;
/*      */     }
/*  478 */     bytes[10] |= temp & 0x80;
/*      */     
/*      */ 
/*      */ 
/*  482 */     for (int i = 0; i <= 10; i++) {
/*  483 */       checksum += bytes[i];
/*  484 */       bytes[i] = bytes[i];
/*      */     }
/*  486 */     bytes[11] = (checksum % 256);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  491 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean configuraCalendarioRelogio(int diaSemana, int diaMes, int mes, int ano, int segundos, int minutos, int horas)
/*      */   {
/*  498 */     int[] bytes = new int[12];
/*  499 */     int checksum = 0;int temp = 0;
/*      */     
/*  501 */     bytes[0] = 207;
/*      */     
/*  503 */     bytes[1] = horas;
/*  504 */     bytes[2] = minutos;
/*  505 */     bytes[3] = segundos;
/*      */     
/*  507 */     bytes[4] = getHoraLigar();
/*  508 */     bytes[5] = getMinutoLigar();
/*      */     
/*  510 */     bytes[6] = getHoraDesligar();
/*  511 */     bytes[7] = getMinutoDesligar();
/*      */     
/*  513 */     bytes[8] = (diaSemana << 5);
/*  514 */     bytes[8] |= diaMes;
/*  515 */     bytes[9] = (mes << 4);
/*  516 */     bytes[9] |= ano - 1998;
/*  517 */     int semana = 0;
/*  518 */     temp = 0;
/*  519 */     boolean[] dias = getDiasSemanaProgramados();
/*      */     
/*  521 */     for (int i = 1; i < 7; i++) {
/*  522 */       if (dias[i] != 0) {
/*  523 */         temp = 1;
/*      */       } else
/*  525 */         temp = 0;
/*  526 */       semana |= temp << 7 - i;
/*      */     }
/*  528 */     if (dias[0] != 0) {
/*  529 */       temp = 1;
/*      */     } else
/*  531 */       temp = 0;
/*  532 */     semana |= temp;
/*  533 */     bytes[10] = semana;
/*  534 */     if (this.modoContinuo) {
/*  535 */       temp = 1;
/*      */     } else {
/*  537 */       temp = 0;
/*      */     }
/*  539 */     bytes[10] |= temp & 0x80;
/*      */     
/*      */ 
/*      */ 
/*  543 */     for (int i = 0; i <= 10; i++) {
/*  544 */       checksum += bytes[i];
/*  545 */       bytes[i] = bytes[i];
/*      */     }
/*  547 */     bytes[11] = (checksum % 256);
/*      */     
/*  549 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean configuraCalendarioRelogioSemana(int diaSemana, int diaMes, int mes, int ano, int segundos, int minutos, int horas, boolean[] dias, int horaLigar, int minutoLigar, int horaDesligar, int minutoDesligar)
/*      */   {
/*  558 */     int[] bytes = new int[12];
/*  559 */     int checksum = 0;int temp = 0;
/*      */     
/*  561 */     bytes[0] = 207;
/*      */     
/*  563 */     bytes[1] = horas;
/*  564 */     bytes[2] = minutos;
/*  565 */     bytes[3] = segundos;
/*      */     
/*  567 */     bytes[4] = horaLigar;
/*  568 */     bytes[5] = minutoLigar;
/*      */     
/*  570 */     bytes[6] = horaDesligar;
/*  571 */     bytes[7] = minutoDesligar;
/*      */     
/*  573 */     bytes[8] = (diaSemana << 5);
/*  574 */     bytes[8] |= diaMes;
/*  575 */     temp = mes;
/*  576 */     bytes[9] = (temp << 4);
/*  577 */     bytes[9] |= ano - 1998;
/*  578 */     int semana = 0;
/*  579 */     temp = 0;
/*      */     
/*  581 */     for (int i = 1; i < 7; i++) {
/*  582 */       if (dias[i] != 0) {
/*  583 */         temp = 1;
/*      */       } else
/*  585 */         temp = 0;
/*  586 */       semana |= temp << 7 - i;
/*      */     }
/*  588 */     if (dias[0] != 0) {
/*  589 */       temp = 1;
/*      */     } else
/*  591 */       temp = 0;
/*  592 */     semana |= temp;
/*      */     
/*  594 */     bytes[10] = semana;
/*  595 */     if (this.modoContinuo) {
/*  596 */       temp = 1;
/*      */     } else {
/*  598 */       temp = 0;
/*      */     }
/*  600 */     bytes[10] |= temp & 0x80;
/*      */     
/*      */ 
/*      */ 
/*  604 */     for (int i = 0; i <= 10; i++) {
/*  605 */       checksum += bytes[i];
/*  606 */       bytes[i] = bytes[i];
/*      */     }
/*  608 */     bytes[11] = (checksum % 256);
/*      */     
/*  610 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean shutdown()
/*      */   {
/*  618 */     int[] bytes = new int[10];
/*  619 */     for (int i = 0; i <= 9; i++) {
/*  620 */       bytes[i] = 222;
/*      */     }
/*  622 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean shutdownReligamento()
/*      */   {
/*  628 */     int[] bytes = new int[10];
/*  629 */     for (int i = 0; i <= 9; i++) {
/*  630 */       bytes[i] = 221;
/*      */     }
/*  632 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean setModoEvento()
/*      */   {
/*  639 */     int[] bytes = new int[2];
/*  640 */     for (int i = 0; i < 2; i++)
/*  641 */       bytes[i] = 206;
/*  642 */     this.modoContinuo = false;
/*      */     
/*  644 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean setModoContinuo()
/*      */   {
/*  650 */     int[] bytes = new int[2];
/*  651 */     for (int i = 0; i < 2; i++)
/*  652 */       bytes[i] = 204;
/*  653 */     this.modoContinuo = true;
/*      */     
/*  655 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean pedidoDumpping()
/*      */   {
/*  662 */     int[] bytes = new int[1];
/*  663 */     bytes[0] = 205;
/*  664 */     this.esperaDumpping = true;
/*  665 */     this.comandoAceito = 1;
/*  666 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Evento[] downloadEventos()
/*      */   {
/*  673 */     if (this.numeroAlarmesMicrosol > 0)
/*      */     {
/*  675 */       Evento[] temp = (Evento[])this.alarmesMicrosol.clone();
/*  676 */       temp[this.numeroAlarmesMicrosol] = null;
/*  677 */       this.numeroAlarmesMicrosol = 0;
/*  678 */       this.alarmesMicrosol = new Evento[100];
/*      */       
/*  680 */       return temp;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  685 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean enviaDados(int[] pacote)
/*      */   {
/*  692 */     this.comandoAceito = 0;
/*      */     
/*  694 */     this.comunicador.sendBytes(pacote);
/*  695 */     this.timeout.schedule(new ProtocoloUPS.TimeoutEnvio(this), 1000L);
/*  696 */     this.esperaACK = true;
/*  697 */     while (this.comandoAceito == 0) {}
/*      */     
/*  699 */     if (this.comandoAceito == 1)
/*      */     {
/*  701 */       this.comandoAceito = 0;
/*  702 */       return true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  707 */     this.comandoAceito = 0;
/*      */     
/*  709 */     this.esperaACK = false;
/*  710 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFrequenciaEntrada(int frequenciaEntrada1, int frequenciaEntrada2)
/*      */   {
/*  723 */     this.frequenciaEntrada = (frequenciaEntrada1 + frequenciaEntrada2 * 256);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setPotenciaReal(int potenciaReal1, int potenciaReal2)
/*      */   {
/*  730 */     this.potenciaReal = (potenciaReal1 + potenciaReal2 * 256);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setAno(int ano)
/*      */   {
/*  737 */     this.ano = ((ano & 0xF) + 1998);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setBateriaCritica(int bateriaCritica)
/*      */   {
/*  744 */     this.bateriaCritica = ((bateriaCritica & 0x4) == 4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCarregandoBateria(int carregandoBateria)
/*      */   {
/*  752 */     if (((carregandoBateria & 0x2) == 2) && (getModoRede())) {
/*  753 */       this.carregandoBateria = true;
/*      */     } else {
/*  755 */       this.carregandoBateria = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setCorrenteSaida(int correnteSaida)
/*      */   {
/*  762 */     this.correnteSaida = correnteSaida;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDiaMes(int diaMes)
/*      */   {
/*  769 */     this.diaMes = (diaMes & 0x1F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setDiaSemana(int diaSemana)
/*      */   {
/*  776 */     this.diaSemana = ((diaSemana & 0xE0) >> 5);
/*      */   }
/*      */   
/*      */ 
/*      */   public void setHora(int hora)
/*      */   {
/*  782 */     this.hora = hora;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setHoraDesligar(int horaDesligar)
/*      */   {
/*  789 */     this.horaDesligar = horaDesligar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setHoraLigar(int horaLigar)
/*      */   {
/*  796 */     this.horaLigar = horaLigar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setMes(int mes)
/*      */   {
/*  803 */     this.mes = ((mes & 0xF0) >> 4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setMinutoDesligar(int minutoDesligar)
/*      */   {
/*  810 */     this.minutoDesligar = minutoDesligar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setMinutoLigar(int minutoLigar)
/*      */   {
/*  817 */     this.minutoLigar = minutoLigar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setMinutos(int minutos)
/*      */   {
/*  824 */     this.minutos = minutos;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setModoBateria(int modoBateria)
/*      */   {
/*  831 */     this.modoBateria = ((modoBateria & 0x8) == 8);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setModoRede(int modoRede)
/*      */   {
/*  838 */     this.modoRede = ((modoRede & 0x20) != 32);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSegundos(int segundos)
/*      */   {
/*  845 */     this.segundos = segundos;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSobrecarga(int sobrecarga)
/*      */   {
/*  852 */     this.sobrecarga = ((sobrecarga & 0x80) == 128);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setSuperAquecimento(int superAquecimento)
/*      */   {
/*  859 */     this.superAquecimento = ((superAquecimento & 0x10) == 16);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTemperaturaUPS(int temperaturaUPS)
/*      */   {
/*  866 */     this.temperaturaUPS = temperaturaUPS;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTensaoBateria(int tensaoBateria)
/*      */   {
/*  873 */     this.tensaoBateria = tensaoBateria;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTensaoEntrada(int tensaoEntrada)
/*      */   {
/*  880 */     this.tensaoEntrada = tensaoEntrada;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTensaoEntrada220(int tensaoEntrada220)
/*      */   {
/*  887 */     if ((!getModoRede()) && (isTensaoEntrada220())) {
/*  888 */       this.tensaoEntrada220 = true;
/*      */     } else {
/*  890 */       this.tensaoEntrada220 = ((tensaoEntrada220 & 0x40) == 64);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTensaoSaida(int tensaoSaida)
/*      */   {
/*  897 */     this.tensaoSaida = tensaoSaida;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setTensaoSaida220(int tensaoSaida220)
/*      */   {
/*  904 */     this.tensaoSaida220 = ((tensaoSaida220 & 0x1) == 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDiasSemanaProgramados(int diasSemanaProgramados)
/*      */   {
/*  913 */     int[] mascara = { 1, 2, 4, 8, 16, 32, 64 };
/*      */     
/*  915 */     switch (this.diaSemana) {
/*      */     case 0: 
/*  917 */       this.diasSemanaProgramados[0] = ((diasSemanaProgramados & mascara[0]) == mascara[0] ? 1 : false);
/*  918 */       this.diasSemanaProgramados[6] = ((diasSemanaProgramados & mascara[1]) == mascara[1] ? 1 : false);
/*  919 */       this.diasSemanaProgramados[5] = ((diasSemanaProgramados & mascara[2]) == mascara[2] ? 1 : false);
/*  920 */       this.diasSemanaProgramados[4] = ((diasSemanaProgramados & mascara[3]) == mascara[3] ? 1 : false);
/*  921 */       this.diasSemanaProgramados[3] = ((diasSemanaProgramados & mascara[4]) == mascara[4] ? 1 : false);
/*  922 */       this.diasSemanaProgramados[2] = ((diasSemanaProgramados & mascara[5]) == mascara[5] ? 1 : false);
/*  923 */       this.diasSemanaProgramados[1] = ((diasSemanaProgramados & mascara[6]) == mascara[6] ? 1 : false);
/*  924 */       break;
/*      */     case 1: 
/*  926 */       this.diasSemanaProgramados[0] = ((diasSemanaProgramados & mascara[1]) == mascara[1] ? 1 : false);
/*  927 */       this.diasSemanaProgramados[6] = ((diasSemanaProgramados & mascara[2]) == mascara[2] ? 1 : false);
/*  928 */       this.diasSemanaProgramados[5] = ((diasSemanaProgramados & mascara[3]) == mascara[3] ? 1 : false);
/*  929 */       this.diasSemanaProgramados[4] = ((diasSemanaProgramados & mascara[4]) == mascara[4] ? 1 : false);
/*  930 */       this.diasSemanaProgramados[3] = ((diasSemanaProgramados & mascara[5]) == mascara[5] ? 1 : false);
/*  931 */       this.diasSemanaProgramados[2] = ((diasSemanaProgramados & mascara[6]) == mascara[6] ? 1 : false);
/*  932 */       this.diasSemanaProgramados[1] = ((diasSemanaProgramados & mascara[0]) == mascara[0] ? 1 : false);
/*  933 */       break;
/*      */     case 2: 
/*  935 */       this.diasSemanaProgramados[0] = ((diasSemanaProgramados & mascara[2]) == mascara[2] ? 1 : false);
/*  936 */       this.diasSemanaProgramados[6] = ((diasSemanaProgramados & mascara[3]) == mascara[3] ? 1 : false);
/*  937 */       this.diasSemanaProgramados[5] = ((diasSemanaProgramados & mascara[4]) == mascara[4] ? 1 : false);
/*  938 */       this.diasSemanaProgramados[4] = ((diasSemanaProgramados & mascara[5]) == mascara[5] ? 1 : false);
/*  939 */       this.diasSemanaProgramados[3] = ((diasSemanaProgramados & mascara[6]) == mascara[6] ? 1 : false);
/*  940 */       this.diasSemanaProgramados[2] = ((diasSemanaProgramados & mascara[0]) == mascara[0] ? 1 : false);
/*  941 */       this.diasSemanaProgramados[1] = ((diasSemanaProgramados & mascara[1]) == mascara[1] ? 1 : false);
/*  942 */       break;
/*      */     case 3: 
/*  944 */       this.diasSemanaProgramados[0] = ((diasSemanaProgramados & mascara[3]) == mascara[3] ? 1 : false);
/*  945 */       this.diasSemanaProgramados[6] = ((diasSemanaProgramados & mascara[4]) == mascara[4] ? 1 : false);
/*  946 */       this.diasSemanaProgramados[5] = ((diasSemanaProgramados & mascara[5]) == mascara[5] ? 1 : false);
/*  947 */       this.diasSemanaProgramados[4] = ((diasSemanaProgramados & mascara[6]) == mascara[6] ? 1 : false);
/*  948 */       this.diasSemanaProgramados[3] = ((diasSemanaProgramados & mascara[0]) == mascara[0] ? 1 : false);
/*  949 */       this.diasSemanaProgramados[2] = ((diasSemanaProgramados & mascara[1]) == mascara[1] ? 1 : false);
/*  950 */       this.diasSemanaProgramados[1] = ((diasSemanaProgramados & mascara[2]) == mascara[2] ? 1 : false);
/*  951 */       break;
/*      */     case 4: 
/*  953 */       this.diasSemanaProgramados[0] = ((diasSemanaProgramados & mascara[4]) == mascara[4] ? 1 : false);
/*  954 */       this.diasSemanaProgramados[6] = ((diasSemanaProgramados & mascara[5]) == mascara[5] ? 1 : false);
/*  955 */       this.diasSemanaProgramados[5] = ((diasSemanaProgramados & mascara[6]) == mascara[6] ? 1 : false);
/*  956 */       this.diasSemanaProgramados[4] = ((diasSemanaProgramados & mascara[0]) == mascara[0] ? 1 : false);
/*  957 */       this.diasSemanaProgramados[3] = ((diasSemanaProgramados & mascara[1]) == mascara[1] ? 1 : false);
/*  958 */       this.diasSemanaProgramados[2] = ((diasSemanaProgramados & mascara[2]) == mascara[2] ? 1 : false);
/*  959 */       this.diasSemanaProgramados[1] = ((diasSemanaProgramados & mascara[3]) == mascara[3] ? 1 : false);
/*  960 */       break;
/*      */     case 5: 
/*  962 */       this.diasSemanaProgramados[0] = ((diasSemanaProgramados & mascara[5]) == mascara[5] ? 1 : false);
/*  963 */       this.diasSemanaProgramados[6] = ((diasSemanaProgramados & mascara[6]) == mascara[6] ? 1 : false);
/*  964 */       this.diasSemanaProgramados[5] = ((diasSemanaProgramados & mascara[0]) == mascara[0] ? 1 : false);
/*  965 */       this.diasSemanaProgramados[4] = ((diasSemanaProgramados & mascara[1]) == mascara[1] ? 1 : false);
/*  966 */       this.diasSemanaProgramados[3] = ((diasSemanaProgramados & mascara[2]) == mascara[2] ? 1 : false);
/*  967 */       this.diasSemanaProgramados[2] = ((diasSemanaProgramados & mascara[3]) == mascara[3] ? 1 : false);
/*  968 */       this.diasSemanaProgramados[1] = ((diasSemanaProgramados & mascara[4]) == mascara[4] ? 1 : false);
/*  969 */       break;
/*      */     case 6: 
/*  971 */       this.diasSemanaProgramados[0] = ((diasSemanaProgramados & mascara[6]) == mascara[6] ? 1 : false);
/*  972 */       this.diasSemanaProgramados[6] = ((diasSemanaProgramados & mascara[0]) == mascara[0] ? 1 : false);
/*  973 */       this.diasSemanaProgramados[5] = ((diasSemanaProgramados & mascara[1]) == mascara[1] ? 1 : false);
/*  974 */       this.diasSemanaProgramados[4] = ((diasSemanaProgramados & mascara[2]) == mascara[2] ? 1 : false);
/*  975 */       this.diasSemanaProgramados[3] = ((diasSemanaProgramados & mascara[3]) == mascara[3] ? 1 : false);
/*  976 */       this.diasSemanaProgramados[2] = ((diasSemanaProgramados & mascara[4]) == mascara[4] ? 1 : false);
/*  977 */       this.diasSemanaProgramados[1] = ((diasSemanaProgramados & mascara[5]) == mascara[5] ? 1 : false);
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */   public void setCabecalhoPacote(int cabecalhoPacote)
/*      */   {
/*  984 */     this.cabecalhoPacote = cabecalhoPacote;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAutonomiaBateria(int autonomiaBateria) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBateriaBaixa(int bateriaBaixa) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBateriaCarregada(int bateriaCarregada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBateriaDescarregada(int bateriaDescarregada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBypassAtivado(int bypassAtivado)
/*      */   {
/* 1023 */     this.bypassAtivado = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCargaElevada(int cargaElevada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setComandoAceito(int comandoAceito) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCorrenteEntrada(int correnteEntrada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setEntradaLigada(int entradaLigada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setExpansorBateria(int expansorBateria) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFatorPotenciaCarga(int fatorPotenciaCarga) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFrequenciaEntrada(int frequenciaEntrada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFrequenciaSaida(int frequenciaSaida) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLimiteInferiorTensaoEntrada(int limiteInferiorTensaoEntrada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLimiteInferiorTensaoSaida(int limiteInferiorTensaoSaida) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLimiteSuperiorTensaoEntrada(int limiteSuperiorTensaoEntrada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLimiteSuperiorTensaoSaida(int limiteSuperiorTensaoSaida) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setModeloUPS(int modeloUPS)
/*      */   {
/* 1115 */     this.modeloUPS = modeloUPS;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setModoBypass(int modoBypass)
/*      */   {
/* 1124 */     this.modoBypass = ((modoBypass & 0x40) == 64);
/* 1125 */     this.bypassAtivado = ((modoBypass & 0x40) == 64);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNumeroTimeout(int numeroTimeout) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPercentualBateria(int percentualBateria) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPotenciaAparente(int potenciaAparente) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPotenciaReal(int potenciaReal) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSaidaLigada(int saidaLigada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTemperaturaElevada(int temperaturaElevada) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTensaoBoost(int tensaoBoost) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTensaoEntradaNominal(int tensaoEntradaNominal) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTensaoSaidaNominal(int tensaoSaidaNominal) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean ligaEntrada()
/*      */   {
/* 1186 */     int[] bytes = new int[10];
/* 1187 */     for (int i = 0; i <= 9; i++) {
/* 1188 */       bytes[i] = 194;
/*      */     }
/* 1190 */     return enviaDados(bytes);
/*      */   }
/*      */   
/* 1193 */   public boolean desligaEntrada() { int[] bytes = new int[10];
/* 1194 */     for (int i = 0; i <= 9; i++) {
/* 1195 */       bytes[i] = 195;
/*      */     }
/* 1197 */     return enviaDados(bytes);
/*      */   }
/*      */   
/*      */   public void pedidoDados() {}
/*      */ }


/* Location:              C:\SGM_LIGHT\SGM_LITE_LINUX.jar!\br\com\schneider\sgm\protocolo\ProtocoloSolis.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */