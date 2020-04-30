package Domain;

public class Correo {

	private String de;
	private String para;
	private String cc;
	private String asunto;
        private String mensaje;

	public Correo(String de,String para, String cc, String asunto,String mensaje) {
		super();
		this.de = de;
		this.para = para;
		this.cc = cc;
		this.asunto = asunto;
                this.mensaje = mensaje;
	}

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

}