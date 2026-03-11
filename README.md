# 🛠️ Hibernate HQL Service Project – KLEF FSAD Exam

> **Maven + Hibernate 5** project implementing **HQL (Hibernate Query Language)**  
> on a `Service` entity — Insert via persistent object & Update via HQL named parameters.

---

## 🗂️ Project Structure

```
HibernateServiceHQL/
├── pom.xml
├── db_setup.sql
└── src/main/
    ├── java/com/klef/fsad/exam/
    │   ├── Service.java        ← Entity Class
    │   └── ClientDemo.java     ← HQL Operations Driver
    └── resources/
        └── hibernate.cfg.xml   ← Hibernate + DB Config
```

---

## 🧩 Entity: `Service`

| Column             | Java Field    | Type     | Notes              |
|--------------------|---------------|----------|--------------------|
| `service_id`       | `id`          | `int`    | PK · AUTO_INCREMENT |
| `service_name`     | `name`        | `String` | NOT NULL           |
| `service_date`     | `date`        | `Date`   |                    |
| `service_status`   | `status`      | `String` |                    |
| `service_type`     | `serviceType` | `String` |                    |
| `service_cost`     | `cost`        | `double` |                    |
| `service_duration` | `duration`    | `String` |                    |
| `service_provider` | `provider`    | `String` |                    |
| `service_location` | `location`    | `String` |                    |

---

## 🔧 HQL Operations

### I. Insert using Persistent Object
```java
Service s = new Service("Web Development", new Date(), "Active", ...);
session.save(s);  // → INSERT INTO service (...)
```

### II. Update Name & Status by ID using HQL Named Parameters
```java
String hql = "UPDATE Service s SET s.name = :newName, s.status = :newStatus WHERE s.id = :serviceId";
Query query = session.createQuery(hql);
query.setParameter("newName",   "Web Dev & Maintenance");
query.setParameter("newStatus", "Completed");
query.setParameter("serviceId", 1);
query.executeUpdate();
```

---

## 🚀 How to Run

### Step 1 – Create MySQL Database
Run in MySQL Workbench or CLI:
```sql
CREATE DATABASE IF NOT EXISTS fsadexam;
```
Or use the provided `db_setup.sql` file.

### Step 2 – Update DB Password
Edit `src/main/resources/hibernate.cfg.xml`:
```xml
<property name="connection.username">root</property>
<property name="connection.password">YOUR_PASSWORD</property>
```

### Step 3 – Build the Project
```cmd
mvn clean compile
```

### Step 4 – Run the Project
```cmd
mvn exec:java
```

---

## 📋 Expected Console Output

```
=======================================================
  KLEF FSAD EXAM – HIBERNATE HQL SERVICE PROJECT
  Database : fsadexam
=======================================================

>>> OPERATION I : Inserting Service Records...

  ✔ [INSERT SUCCESS] Service saved with Auto ID = 1
  ✔ [INSERT SUCCESS] Service saved with Auto ID = 2
  ✔ [INSERT SUCCESS] Service saved with Auto ID = 3

--- All Records After INSERT ---
  Total Records Found : 3
  Service { ID=1, Name=Web Development, Status=Active ... }
  Service { ID=2, Name=Network Setup,   Status=Pending ... }
  Service { ID=3, Name=Cloud Migration, Status=Active ... }

>>> OPERATION II : Updating Name & Status using HQL Named Parameters...

  ✔ [HQL UPDATE SUCCESS] 1 record(s) updated for ID = 1
  ✔ [HQL UPDATE SUCCESS] 1 record(s) updated for ID = 2
  ⚠ [HQL UPDATE] No record found with ID = 9999

--- All Records After HQL UPDATE ---
  Service { ID=1, Name=Web Dev & Maintenance, Status=Completed ... }
  Service { ID=2, Name=Advanced Network Setup, Status=Active ... }
  Service { ID=3, Name=Cloud Migration,        Status=Active ... }

=======================================================
  ALL HQL OPERATIONS COMPLETED SUCCESSFULLY
=======================================================
```

---

## ⚙️ Tech Stack

| Technology | Version       |
|------------|---------------|
| Java       | 11+           |
| Maven      | 3.6+          |
| Hibernate  | 5.6.15.Final  |
| MySQL      | 8.x           |

**Package:** `com.klef.fsad.exam` | **Database:** `fsadexam`

---

## 👤 Author

**KLEF Student** — Full Stack Application Development (FSAD) Exam
