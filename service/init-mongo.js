db.createUser(
    {
        user: "notes",
        pwd: "“zx12sd34",
        "roles" : [
                {
                        "role" : "readWrite",
                        "db" : "admin"
                },
                {
                        "role" : "root",
                        "db" : "admin"
                },
                {
                        "role" : "readWrite",
                        "db" : "notes"
                }
        ]
    }
)