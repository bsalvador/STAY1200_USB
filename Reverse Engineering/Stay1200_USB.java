package br.com.schneider.sgm.dispositivos;

import br.com.schneider.sgm.eventos.Evento;
import br.com.schneider.sgm.protocolo.ProtocoloPS;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Stay1200_USB
  extends PS800
{
  protected int estadoReles;
  
  public Stay1200_USB(ProtocoloPS protocol)
  {
    super(protocol);
    instanciasGlobais();
    protocol.setModeloUPS(10);
  }
  
  public void instanciasGlobais()
  {
    this.TENSAO_ENTRADA_F1 = 1.64F;
    this.TENSAO_ENTRADA_F2 = 9.34F;
    this.TENSAO_BATERIA_F1 = 0.1551D;
    this.TENSAO_BATERIA_F2 = 0.2525D;
    
    this.TENSAO_MIN_BATERIA = 20.0F;
    this.TENSAO_MAX_BATERIA = 29.5F;
    this.TENSAO_FLUT_BATERIA = 27.0F;
    
    this.CORRENTE_SAIDA_F1_MR = 0.12970000389100012D;
    this.CORRENTE_SAIDA_F2_MR = 0.5387060281204546D;
    
    this.CORRENTE_SAIDA_F1_MI = 0.1372D;
    this.CORRENTE_SAIDA_F2_MI = 0.3456D;
    
    this.POTENCIAUTIL_DETECCAO_CURVA1_F1 = new double[] { 0.079D, 0.089D, 0.0972D, 0.0D, 0.0805D, 0.0883D, 0.0D, 0.0981D };
    this.POTENCIAUTIL_DETECCAO_CURVA2_F1 = new double[] { 0.0763D, 0.081D, 0.0919D, 0.0D, 0.0741D, 0.0828D, 0.0D, 0.0938D };
    this.POTENCIAUTIL_DETECCAO_CURVA3_F1 = new double[] { 0.0744D, 0.0808D, 0.0885D, 0.0D, 0.0732D, 0.084D, 0.0D, 0.0955D };
    
    this.POTENCIAUTIL_DETECCAO_CURVA1_F2 = new double[] { 49.107D, 45.449D, 48.092D, 0.0D, 43.633D, 47.585D, 0.0D, 48.831D };
    this.POTENCIAUTIL_DETECCAO_CURVA2_F2 = new double[] { 81.732D, 94.459D, 86.686D, 0.0D, 84.657D, 84.999D, 0.0D, 78.097D };
    this.POTENCIAUTIL_DETECCAO_CURVA3_F2 = new double[] { 122.06D, 122.9D, 125.75D, 0.0D, 120.39D, 108.52D, 0.0D, 92.239D };
    
    this.POTENCIAUTIL_CURVA1_F1 = new double[] { 0.08040007075206226D, 0.0894D, 0.0999D, 0.0D, 0.0813D, 0.0905D, 0.0D, 0.1005D };
    this.POTENCIAUTIL_CURVA2_F1 = new double[] { 0.08630063689870031D, 0.0946D, 0.1068D, 0.0D, 0.086D, 0.0967D, 0.0D, 0.1088D };
    this.POTENCIAUTIL_CURVA3_F1 = new double[] { 0.0896001146881468D, 0.0991D, 0.1116D, 0.0D, 0.0967D, 0.1068D, 0.0D, 0.1169D };
    
    this.POTENCIAUTIL_CURVA1_F2 = new double[] { 45.292D, 41.928D, 41.727D, 0.0D, 40.269D, 41.81D, 0.0D, 43.458D };
    this.POTENCIAUTIL_CURVA2_F2 = new double[] { 8.3927D, 9.2393D, 8.2852D, 0.0D, 8.301D, 6.7636D, 0.0D, 8.2842D };
    this.POTENCIAUTIL_CURVA3_F2 = new double[] { -31.115D, -33.777D, -33.826D, 0.0D, -59.513D, -57.729D, 0.0D, -41.333D };
    
    this.TENSAO_SAIDA_F1_MR = new double[] { 1.1549D, 1.0925D, 0.0D, 0.0D, 1.0929D, 1.0885D, 0.0D, 0.8654262224145391D };
    this.TENSAO_SAIDA_F2_MR = new double[] { -6.9157D, 11.026D, 10.43D, 0.0D, -0.6109D, 12.18D, 0.0D, 13.677D };
    
    this.TENSAO_SAIDA_F2_MI = new double[] { 5.59D, 9.47D, 13.7D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
    this.TENSAO_SAIDA_F1_MI = new double[] { 7.9D, 9.1D, 17.6D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
    
    this.autonomia100 = new int[] { 26, 25, 24, 23, 22, 21, 20, 18, 17, 16, 14, 13, 11, 9, 7, 4, 2, 1 };
  }
  
  public void setSobrecarga(boolean sobrecarga)
  {
    if (this.potenciaReal > 655.0F)
    {
      this.sobrecarga = true;
      this.cargaElevada = true;
    }
    else
    {
      this.sobrecarga = false;
      this.cargaElevada = false;
    }
  }
  
  public void setPotenciaReal(int potenciaReal)
  {
    double potVA1 = 5.968D * this.potenciaAparente - 284.36D;
    double potVA2 = 7.149D * this.potenciaAparente - 567.18D;
    double potLin = 0.1664D * potenciaReal + 49.182D;
    double potRe = 0.1519D * potenciaReal + 32.644D;
    if (Math.abs(potVA1 - potenciaReal) < Math.abs(potVA2 - potenciaReal)) {
      this.potenciaReal = ((float)potLin);
    } else {
      this.potenciaReal = ((float)potRe);
    }
    if (this.correnteSaida < 0.7D) {
      this.potenciaReal = this.potenciaAparente;
    }
    if (this.potenciaAparente < this.potenciaReal)
    {
      float f = this.potenciaAparente;
      this.potenciaAparente = this.potenciaReal;
      this.potenciaReal = f;
    }
  }
  
  public void setAutonomiaBateria(int autonomiaBateria)
  {
    if (this.potenciaReal < 120.0F)
    {
      this.autonomiaBateria = Math.round((float)(0.0982D * Math.pow(autonomiaBateria, 2.0D) - 27.131D * autonomiaBateria + 1875.18D));
      if (this.autonomiaBateria > 60) {
        this.autonomiaBateria = 60;
      }
    }
    else if (this.potenciaReal < 230.0F)
    {
      this.autonomiaBateria = Math.round((float)(0.0663D * Math.pow(autonomiaBateria, 2.0D) - 18.252D * autonomiaBateria + 1257.2D));
      if (this.autonomiaBateria > 30) {
        this.autonomiaBateria = 30;
      }
    }
    else if (this.potenciaReal < 330.0F)
    {
      this.autonomiaBateria = Math.round((float)(0.0225D * Math.pow(autonomiaBateria, 2.0D) - 5.9113D * autonomiaBateria + 389.08D));
      if (this.autonomiaBateria > 13) {
        this.autonomiaBateria = 13;
      }
    }
    else if (this.potenciaReal < 430.0F)
    {
      this.autonomiaBateria = Math.round((float)(9.0E-4D * Math.pow(autonomiaBateria, 2.0D) + 3.3172D * autonomiaBateria - 272.34D));
      if (this.autonomiaBateria > 10) {
        this.autonomiaBateria = 10;
      }
    }
    else if (this.potenciaReal < 530.0F)
    {
      this.autonomiaBateria = Math.round((float)(0.5711D * Math.pow(this.tensaoBateria, 2.0D) - 23.484D * this.tensaoBateria + 241.81D));
      if (this.autonomiaBateria > 8) {
        this.autonomiaBateria = 8;
      }
    }
    else if (this.potenciaReal < 630.0F)
    {
      this.autonomiaBateria = Math.round((float)(0.0091D * Math.pow(autonomiaBateria, 2.0D) - 2.3133D * autonomiaBateria + 147.62D));
      if (this.autonomiaBateria > 8) {
        this.autonomiaBateria = 8;
      }
    }
    if (this.expansorBateria != 0) {
      this.autonomiaBateria = ((int)(this.autonomiaBateria * (this.expansorBateria / 9.75D)));
    }
  }
  
  public void setTensaoBateria(int tensaoBateria)
  {
    this.tensaoBateria = ((float)(this.TENSAO_BATERIA_F1 * tensaoBateria + this.TENSAO_BATERIA_F2));
  }
  
  public void setTensaoEntrada(int tensaoEntrada)
  {
    if (isTensaoSaida220()) {
      this.tensaoEntrada = ((int)(this.TENSAO_ENTRADA_F1 * tensaoEntrada + this.TENSAO_ENTRADA_F2));
    } else {
      this.tensaoEntrada = ((int)(this.TENSAO_ENTRADA_F1 * tensaoEntrada + this.TENSAO_ENTRADA_F2 - 3.0F));
    }
  }
  
  public void setCorrenteSaida(int correnteSaida)
  {
    if (this.modoRede) {
      this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MR * correnteSaida + this.CORRENTE_SAIDA_F2_MR));
    } else if (this.saidaLigada) {
      this.correnteSaida = ((float)(this.CORRENTE_SAIDA_F1_MI * correnteSaida + this.CORRENTE_SAIDA_F2_MI));
    } else {
      this.correnteSaida = 0.0F;
    }
    if (correnteSaida == 0) {
      this.correnteSaida = 0.0F;
    }
  }
  
  public boolean isAutoTesteBateriaAvaliable()
  {
    return true;
  }
  
  public int calculaEstadoBaterias(float bateriaInicial, float bateriaFinal, float Potencia, int tempo)
  {
    Calendar calendar = new GregorianCalendar();
    
    int mesAtual = calendar.get(2) + 1;
    int anoAtual = calendar.get(1);
    
    Evento evt = new Evento(29, calendar.get(11), calendar.get(12), calendar.get(13), getDiaMes(), mesAtual, anoAtual);
    
    this.protocoloUPS.addEvento(evt);
    
    return 0;
  }
  
  public void setBateriaBaixa(boolean bateriaBaixa)
  {
    if (this.modoBateria) {
      this.bateriaBaixa = (this.tensaoBateria < 19.0F);
    }
  }
}
