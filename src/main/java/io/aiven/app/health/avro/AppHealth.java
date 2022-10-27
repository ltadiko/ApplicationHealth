/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package io.aiven.app.health.avro;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@org.apache.avro.specific.AvroGenerated
public class AppHealth extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -6354581098655506979L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AppHealth\",\"namespace\":\"io.aiven.app.health.avro\",\"fields\":[{\"name\":\"websiteid\",\"type\":\"int\"},{\"name\":\"status\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<AppHealth> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<AppHealth> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<AppHealth> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<AppHealth> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<AppHealth> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this AppHealth to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a AppHealth from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a AppHealth instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static AppHealth fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private int websiteid;
  private java.lang.String status;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public AppHealth() {}

  /**
   * All-args constructor.
   * @param websiteid The new value for websiteid
   * @param status The new value for status
   */
  public AppHealth(java.lang.Integer websiteid, java.lang.String status) {
    this.websiteid = websiteid;
    this.status = status;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return websiteid;
    case 1: return status;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: websiteid = (java.lang.Integer)value$; break;
    case 1: status = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'websiteid' field.
   * @return The value of the 'websiteid' field.
   */
  public int getWebsiteid() {
    return websiteid;
  }


  /**
   * Sets the value of the 'websiteid' field.
   * @param value the value to set.
   */
  public void setWebsiteid(int value) {
    this.websiteid = value;
  }

  /**
   * Gets the value of the 'status' field.
   * @return The value of the 'status' field.
   */
  public java.lang.String getStatus() {
    return status;
  }


  /**
   * Sets the value of the 'status' field.
   * @param value the value to set.
   */
  public void setStatus(java.lang.String value) {
    this.status = value;
  }

  /**
   * Creates a new AppHealth RecordBuilder.
   * @return A new AppHealth RecordBuilder
   */
  public static io.aiven.app.health.avro.AppHealth.Builder newBuilder() {
    return new io.aiven.app.health.avro.AppHealth.Builder();
  }

  /**
   * Creates a new AppHealth RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new AppHealth RecordBuilder
   */
  public static io.aiven.app.health.avro.AppHealth.Builder newBuilder(io.aiven.app.health.avro.AppHealth.Builder other) {
    if (other == null) {
      return new io.aiven.app.health.avro.AppHealth.Builder();
    } else {
      return new io.aiven.app.health.avro.AppHealth.Builder(other);
    }
  }

  /**
   * Creates a new AppHealth RecordBuilder by copying an existing AppHealth instance.
   * @param other The existing instance to copy.
   * @return A new AppHealth RecordBuilder
   */
  public static io.aiven.app.health.avro.AppHealth.Builder newBuilder(io.aiven.app.health.avro.AppHealth other) {
    if (other == null) {
      return new io.aiven.app.health.avro.AppHealth.Builder();
    } else {
      return new io.aiven.app.health.avro.AppHealth.Builder(other);
    }
  }

  /**
   * RecordBuilder for AppHealth instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AppHealth>
    implements org.apache.avro.data.RecordBuilder<AppHealth> {

    private int websiteid;
    private java.lang.String status;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(io.aiven.app.health.avro.AppHealth.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.websiteid)) {
        this.websiteid = data().deepCopy(fields()[0].schema(), other.websiteid);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.status)) {
        this.status = data().deepCopy(fields()[1].schema(), other.status);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing AppHealth instance
     * @param other The existing instance to copy.
     */
    private Builder(io.aiven.app.health.avro.AppHealth other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.websiteid)) {
        this.websiteid = data().deepCopy(fields()[0].schema(), other.websiteid);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.status)) {
        this.status = data().deepCopy(fields()[1].schema(), other.status);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'websiteid' field.
      * @return The value.
      */
    public int getWebsiteid() {
      return websiteid;
    }


    /**
      * Sets the value of the 'websiteid' field.
      * @param value The value of 'websiteid'.
      * @return This builder.
      */
    public io.aiven.app.health.avro.AppHealth.Builder setWebsiteid(int value) {
      validate(fields()[0], value);
      this.websiteid = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'websiteid' field has been set.
      * @return True if the 'websiteid' field has been set, false otherwise.
      */
    public boolean hasWebsiteid() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'websiteid' field.
      * @return This builder.
      */
    public io.aiven.app.health.avro.AppHealth.Builder clearWebsiteid() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'status' field.
      * @return The value.
      */
    public java.lang.String getStatus() {
      return status;
    }


    /**
      * Sets the value of the 'status' field.
      * @param value The value of 'status'.
      * @return This builder.
      */
    public io.aiven.app.health.avro.AppHealth.Builder setStatus(java.lang.String value) {
      validate(fields()[1], value);
      this.status = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'status' field has been set.
      * @return True if the 'status' field has been set, false otherwise.
      */
    public boolean hasStatus() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'status' field.
      * @return This builder.
      */
    public io.aiven.app.health.avro.AppHealth.Builder clearStatus() {
      status = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AppHealth build() {
      try {
        AppHealth record = new AppHealth();
        record.websiteid = fieldSetFlags()[0] ? this.websiteid : (java.lang.Integer) defaultValue(fields()[0]);
        record.status = fieldSetFlags()[1] ? this.status : (java.lang.String) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<AppHealth>
    WRITER$ = (org.apache.avro.io.DatumWriter<AppHealth>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<AppHealth>
    READER$ = (org.apache.avro.io.DatumReader<AppHealth>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeInt(this.websiteid);

    out.writeString(this.status);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.websiteid = in.readInt();

      this.status = in.readString();

    } else {
      for (int i = 0; i < 2; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.websiteid = in.readInt();
          break;

        case 1:
          this.status = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










