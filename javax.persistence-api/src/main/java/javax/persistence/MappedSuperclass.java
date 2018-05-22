/*******************************************************************************
 * Copyright (c) 2008 - 2013 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * このクラスを継承することでエンティティにマッピング情報が適用されるクラスを指定します。
 * 
 * マッピングされたスーパークラスには分割されたテーブルが定義されません。
 * 
 * <p> <code>MappedSuperclass</code>アノテーションで指定されたクラスは、マップドスーパークラス自体のテーブルが存在せずマッピングはそのサブクラスにのみ適用される点を除き、エンティティと同じ方法でマッピングできます。
 * サブクラスに適用すると、継承されたマッピングがサブクラスのテーブルのコンテキストで適用されます。
 * マッピング情報は、<code>AttributeOverride</code>アノテーションまたは<code>AssociationOverride</code>アノテーションまたはそれらに対応するXML要素を使用してそのようなサブクラスでオーバーライドできます。
 *
 * <pre>
 *    Example: マップドスーパークラスとして固められたクラス
 *
 *    &#064;MappedSuperclass
 *    public class Employee {
 *    
 *        &#064;Id protected Integer empId;
 *        &#064;Version protected Integer version;
 *        &#064;ManyToOne &#064;JoinColumn(name="ADDR")
 *        protected Address address;
 *    
 *        public Integer getEmpId() { ... }
 *        public void setEmpId(Integer id) { ... }
 *        public Address getAddress() { ... }
 *        public void setAddress(Address addr) { ... }
 *    }
 *    
 *    // デフォルトはFTEMPLOYEEテーブル
 *    &#064;Entity
 *    public class FTEmployee extends Employee {
 *    
 *        // FTEMPLOYEE.EMPIDにマッピングされた継承されたempIdフィールド
 *        // FTEMPLOYEE.VERSIONにマッピングされた継承されたversionフィールド
 *        // FTEMPLOYEE.ADDR(fk)にマッピングされた継承されたaddressフィールド
 *    
 *        // デフォルトはFTEMPLOYEE.SALARY
 *        protected Integer salary;
 *    
 *        public FTEmployee() {}
 *    
 *        public Integer getSalary() { ... }
 *    
 *        public void setSalary(Integer salary) { ... }
 *    }
 *    
 *    &#064;Entity &#064;Table(name="PT_EMP")
 *    &#064;AssociationOverride(
 *        name="address", 
 *        joincolumns=&#064;JoinColumn(name="ADDR_ID"))
 *    public class PartTimeEmployee extends Employee {
 *    
 *        // FTEMPLOYEE.EMPIDにマッピングされた継承されたempIdフィールド
 *        // FTEMPLOYEE.VERSIONにマッピングされた継承されたversionフィールド
 *        // FTEMPLOYEE.ADDR(fk)にマッピングされた継承されたaddressフィールド
 *        &#064;Column(name="WAGE")
 *        protected Float hourlyWage;
 *    
 *        public PartTimeEmployee() {}
 *    
 *        public Float getHourlyWage() { ... }
 *        public void setHourlyWage(Float wage) { ... }
 *    }
 * </pre>
 *
 * @see AttributeOverride 
 * @see AssociationOverride
 * @since Java Persistence 1.0
 */
@Documented
@Target({TYPE})
@Retention(RUNTIME)
public @interface MappedSuperclass {
}
