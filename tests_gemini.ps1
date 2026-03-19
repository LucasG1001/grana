$key = "AIzaSyBg1Hd1FizgbixK-sbn_gqSjZgSnynqdyE"
$url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash?key=$key"
$body = @{
    contents = @(
        @{
            parts = @(
                @{
                    text = "Hello, respond with a JSON object."
                }
            )
        }
    )
} | ConvertTo-Json -Depth 5

try {
    $response = Invoke-RestMethod -Uri $url -Method Post -Body $body -ContentType "application/json"
    $response | ConvertTo-Json -Depth 10
} catch {
    $stream = $_.Exception.Response.GetResponseStream()
    $reader = New-Object System.IO.StreamReader($stream)
    $errorBody = $reader.ReadToEnd()
    Write-Host "ERROR BODY:"
    Write-Host $errorBody
}
